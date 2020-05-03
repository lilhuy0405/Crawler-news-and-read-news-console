package model.crawler;

import entity.Article;
import helper.DateConvertHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CrawlerManager {

    /************************************************* 1. VNexpress crawler  ****************************************/

    public Article crawlVnExpress(String url) {
        Article article = new Article();
        try {
            System.out.println("bắt đầu lấy tin từ url: " + url);
            Document document = Jsoup.connect(url).get();
            String title = document.select("h1.title-detail").text();
            String description = document.select(".description").text();
            String dateString = document.select(".date").text().trim();
            //convert to java util date
            java.util.Date date = DateConvertHelper.convertVnExpressDate(dateString);

            String content = document.select("article.fck_detail").text();

            String source = "vnexpress.net";
            String category = document.select("ul.breadcrumb>li:first-child").text();
            //check null
            if (category == null || title == null || description == null || dateString == null || content == null) {
                return null;
            }
            //set value for article
            article.setUrl(url);
            article.setCategory(category);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setPublicDate(date);
            article.setSource(source);
            //log
            System.out.println("lấy tin từ url" + url + " thành công!");
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lỗi khi lấy tin tại url + " + url + " " + e.getMessage());
            return null;
        }
        return article;
    }

    /************************************************* 2. dantri crawler ****************************************/

    public Article crawlDantri(String url) {
        Article article = new Article();

        try {
            Document document = Jsoup.connect(url).get();
            System.out.println("bắt đầu lấy tin từ url: " + url);
            //crawl title, description, category, content, date,
            String title = document.select("p.detail_subtitle+h1.fon31.mgb15").text();

            String description = document.select("h2.sapo.mt1").text().replace("Dân trí", "").trim();

            String category = document.select("ol.breadcrumb li:nth-child(2)").text().replace("›", "").trim();

            String content = document.select("#divNewsContent").text();

            String dateString = document.select("div.box26 span.fr.fon7").text().trim();

            //convert to date object
            java.util.Date publicDate = DateConvertHelper.convertDantriDate(dateString);
            //check null
            if (title == null || dateString == null || description == null || category == null || content == null) {
                return null;
            }
            //set value
            article.setUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setSource("Dantri.vn");
            article.setCategory(category);
            article.setPublicDate(publicDate);
            //log and return
            System.out.println("lấy tin thành công từ url: " + url);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.printf("lỗi khi lấy tin tại url : %s lỗi là: %s \n", url, e.getMessage());
            return null;
        }
        return article;
    }

    /************************************************* 3.kenh14 crawler ****************************************/

    public Article crawlKenh14(String url) {
        Article article = new Article();
        try {
            Document document = Jsoup.connect(url).get();
            System.out.println("bắt đầu lấy tin từ url: " + url);
            //crawl title, description, category, content, date,
            String title = document.select("h1.kbwc-title").text();

            String description = document.select(".knc-sapo").text();

            String category = document.select("li.kbwsli.active").text();

            String content = document.select("div.knc-content").text();

            String dateString = document.select("span.kbwcm-time").text().trim();

            //convert to java date
            java.util.Date publicDate = DateConvertHelper.convertKenh14Date(dateString);
            //check null
            if (dateString == null || title == null || category == null || content == null || description == null) {
                return null;
            }
            //set value
            article.setUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setSource("kenh14.vn");
            article.setCategory(category);
            article.setPublicDate(publicDate);
            //log and return
            System.out.println("lấy tin thành công từ url: " + url);
        } catch (IOException e) {
            System.err.println("lấy tin thất bại tại url: " + url + " lỗi là: " + e.getMessage());
            return null;
        }
        return article;
    }

    /************************************************* 4.Vietnamnet crawler ****************************************/
    public Article crawlVietnamnet(String url) {
        Article article = new Article();
        try {
            Document document = Jsoup.connect(url).get();
            System.out.println("bắt đầu lấy tin từ url: " + url);
            //crawl title, description, category, content, date,
            String title = document.select("h1.title.f-22").text();

            String description = document.select("div.ArticleLead").text();

            String category = document.select("div.top-cate-head-subcate-child").text().replace("❯", "");

            String content = document.select("#ArticleContent").text();

            String dateString = document.select("span.ArticleDate").text().trim();

            //convert to java date
            java.util.Date publicDate = DateConvertHelper.convertVietnamnetDate(dateString);

            //check null
            if (description == null || title == null || category == null || content == null || dateString == null) {
                return null;
            }
            //set value
            article.setUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setSource("vietnamnet.vn");
            article.setCategory(category);
            article.setPublicDate(publicDate);
            //log and return
            System.out.println("lấy tin thành công từ url: " + url);
        } catch (IOException e) {
            System.err.println("lấy tin thất bại tại url: " + url + " lỗi là: " + e.getMessage());
            return null;
        }
        return article;
    }

    /************************************************* 5. Zing news crawler ****************************************/
    public Article crawlZingnews(String url) {
        System.out.println("bắt đầu lấy tin từ url: " + url);
        Article article = new Article();
        try {
            Document document = Jsoup.connect(url).get();
            //crawl title, description, content, date, category
            String title = document.select("h1.the-article-title").text();

            String description = document.select("p.the-article-summary").text();

            String content = document.select("div.the-article-body").text();

            String category = document.select(".the-article-category").text();

            String dateString = document.select(".the-article-publish").text().replace("đăng lúc", "").trim();

            //convert to java date
            java.util.Date publicDate = DateConvertHelper.convertZingnewsDate(dateString);

            //check null
            if (title == null || dateString == null || description == null || category == null || content == null) {
                return null;
            }
            //set value
            article.setUrl(url);
            article.setSource("zingnews.vn");
            article.setDescription(description);
            article.setCategory(category);
            article.setPublicDate(publicDate);
            article.setTitle(title);
            article.setContent(content);
            System.out.println("lấy tin thành công từ url: " + url);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lấy tin thất bại tại url +" + url + " lỗi là: " + e.getMessage());
        }

        return article;
    }

}
