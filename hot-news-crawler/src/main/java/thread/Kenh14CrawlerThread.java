package thread;

import entity.Article;
import model.crawler.CrawlerManager;
import model.database.DataBaseModel;

public class Kenh14CrawlerThread extends Thread {
    private String url;
    private CrawlerManager crawlerManager;
    private DataBaseModel dataBaseModel;
    public Kenh14CrawlerThread(String url) {
        this.url = url;
        crawlerManager = new CrawlerManager();
        dataBaseModel = new DataBaseModel();
    }

    @Override
    public void run() {
        // lay tin tu url hien tai
        Article article = crawlerManager.crawlKenh14(url);
        //them article vao database
        dataBaseModel.insertArticleToDatabase(article);
    }
}
