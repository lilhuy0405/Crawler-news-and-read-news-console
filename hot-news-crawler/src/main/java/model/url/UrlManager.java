package model.url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class UrlManager {
    //1. dan tri url
    public HashSet<String> getDantriUrls(String rootUrl) {
        HashSet<String> urls = new HashSet<String>();
        try {
            Document document = Jsoup.connect(rootUrl).get();
            Elements aEls = document.select("a[href]");
            for (Element aEl:
                    aEls) {
                String articleUrl = aEl.attr("href");

                if(articleUrl.contains("htm") && !(articleUrl.contains("video") || articleUrl.contains("event")) && articleUrl.length() > 40){

                    if(!articleUrl.contains("https://dantri.com.vn")) {
                        articleUrl = "https://dantri.com.vn" +articleUrl;
                    }

                    urls.add(articleUrl);
                }
            }
            System.out.printf("lấy thành công %d links từ %s !\n", urls.size(), rootUrl);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lấy link thất bại.... lỗi: " + e.getMessage());
        }
        return urls;
    }
    // 2. kenh 14 url
    public HashSet<String> getKenh14Urls(String rootUrl) {
        HashSet<String> urls = new HashSet<String>();

        try {
            Document document = Jsoup.connect(rootUrl).get();
            Elements aElements = document.select("div.kbwcb-left.fl a");
            if (aElements.size() == 0) {
                return null;
            }
            for (Element aEl : aElements) {
                String articleUrl = aEl.attr("href");
                if(articleUrl.contains(".chn") && articleUrl.length() > 40 && !articleUrl.contains("#")) {
                    //link cua kenh 14 ko p url tuyet doi --> them domain name
                    // length > 40 de bo qua cac navigation url
                    if(!articleUrl.contains("https://kenh14.vn")) {
                        // thêm domain name;
                        articleUrl = "https://kenh14.vn" + articleUrl;
                    }
                    //add to hash set
                    urls.add(articleUrl);
                }

            }
            System.out.printf("đã lấy %d link thành công từ %s !\n", urls.size(), rootUrl);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lấy link thất bại... Lỗi: " + e.getMessage());
        }
        return urls;
    }
    //3. vietnamnet url
    public HashSet<String> getVietnamnetUrls(String rootUrl) {
        HashSet<String> urls = new HashSet<String>();
        try {
            Document document = Jsoup.connect(rootUrl).get();
            Elements aEls = document.select("div.d-ib a");
            if(aEls.size() == 0) return null;
            for (Element el:
                    aEls) {
                String articleUrl = el.attr("href");
                if(articleUrl.contains("html")) {
                    if(!articleUrl.contains("https://vietnamnet.vn")) {
                        articleUrl = "https://vietnamnet.vn" + articleUrl;
                    }
                    urls.add(articleUrl);
                }

            }
            System.out.printf("lấy thành công %d link từ %s !\n", urls.size(),rootUrl);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lấy link thất bại... Lỗi : " + e.getMessage());
        }
        return urls;
    }
    //4. vnexpres urls
    public HashSet<String> getVnExpressUrls(String rootURL) {
        HashSet<String> urls = new HashSet<String>();
        try {
            Document document = Jsoup.connect(rootURL).get();
            //get all aElements
            Elements aElements = document.select("a[href]");
            if (aElements.size() == 0) {
                return null;
            }
            for (int i = 0; i < aElements.size(); i++) {
                String articleUrl = aElements.get(i).attr("href");
                if (articleUrl.contains("https") && articleUrl.contains(".html") && !articleUrl.contains("#box_comment")) {
                    //add to hashset
                    urls.add(articleUrl);
                } //end if
            } //end for
            System.out.printf("lấy %d links  thành công từ %s !\n", urls.size(), rootURL);
        } catch (IOException e) {
            System.err.println("lấy link thất bại.... lỗi: "+ e.getMessage());
        }
        return urls;
    }
    //5. Zingnews urls
    public HashSet<String> getZingUrls(String rootUrl) {
        HashSet<String> urls = new HashSet<String>();
        try {
            Document document = Jsoup.connect(rootUrl).get();
            Elements aEls = document.select("a[href]");
            if(aEls.size() == 0) {
                return null;
            }
            for (int i = 0; i < aEls.size(); i++) {
                String articleUrl = aEls.get(i).attr("href");
                if(articleUrl.contains(".html") && !articleUrl.contains("#") && articleUrl.length() > 50) {
                    // length > 50 de bo qua navigation urls
                    if(!articleUrl.contains("https://zingnews.vn")) {
                        // thêm domain name;
                        articleUrl = "https://zingnews.vn" + articleUrl;
                    }
                    // add to hashset
                    urls.add(articleUrl);
                }
            }
            System.out.printf("đã lấy thành công %d links từ %s !\n", urls.size(), rootUrl);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("lấy link thất bại.. lỖi là: " + e.getMessage());
        }
        return urls;
    }

}
