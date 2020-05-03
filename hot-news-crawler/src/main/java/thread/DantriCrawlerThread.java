package thread;

import entity.Article;
import model.crawler.CrawlerManager;
import model.database.DataBaseModel;

public class DantriCrawlerThread extends Thread {
    private String url;
    private CrawlerManager crawlerManager;
    private DataBaseModel dataBaseModel;
    public DantriCrawlerThread(String url) {
        this.url = url;
        crawlerManager = new CrawlerManager();
        dataBaseModel = new DataBaseModel();
    }

    @Override
    public void run() {
        // lay tin tu url hien tai
        Article article = crawlerManager.crawlDantri(url);
        //them article vao database
        dataBaseModel.insertArticleToDatabase(article);
    }
}
