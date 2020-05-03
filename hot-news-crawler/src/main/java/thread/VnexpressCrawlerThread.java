package thread;

import entity.Article;
import model.crawler.CrawlerManager;
import model.database.DataBaseModel;

public class VnexpressCrawlerThread extends Thread {
    private String url;
    private CrawlerManager crawlerManager;
    private DataBaseModel dataBaseModel;
    public VnexpressCrawlerThread(String url) {
        this.url = url;
        crawlerManager = new CrawlerManager();
        dataBaseModel = new DataBaseModel();
    }

    @Override
    public void run() {
        // lay tin tu url hien tai
        Article article = crawlerManager.crawlVnExpress(url);
        //them article vao database
        dataBaseModel.insertArticleToDatabase(article);
    }
}
