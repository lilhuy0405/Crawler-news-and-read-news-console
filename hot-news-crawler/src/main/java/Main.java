import entity.Article;
import model.crawler.CrawlerManager;
import model.database.DataBaseModel;
import model.url.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import thread.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //get start time
        long start = Calendar.getInstance().getTimeInMillis();

        UrlManager urlManager = new UrlManager();
        /****CREATE HASHSET TO STORE LINKS****/
        //1. kenh14
        HashSet<String> kenh14XaHoiUrls = urlManager.getKenh14Urls("https://kenh14.vn/xa-hoi.chn");
        HashSet<String> kenh14AllUrs = urlManager.getKenh14Urls("https://kenh14.vn/star.chn");
        kenh14AllUrs.addAll(kenh14XaHoiUrls);
        System.out.println(kenh14AllUrs.size() + " links từ kenh14");
        //2.vnexpress
        HashSet<String> vnExpressGiaiTriUrl = urlManager.getVnExpressUrls("https://vnexpress.net/giai-tri");
        HashSet<String> vnExpressAllUrs = urlManager.getVnExpressUrls("https://vnexpress.net/thoi-su");
        vnExpressAllUrs.addAll(vnExpressGiaiTriUrl);
        System.out.printf("có %d link từ vnexpress\n", vnExpressAllUrs.size());
        //3.vietnamnet
        HashSet<String> vietnamnetAllUrs = urlManager.getVietnamnetUrls("https://vietnamnet.vn/vn/thoi-su/chinh-tri/");
        System.out.printf("có %d links từ vietnamnet\n", vietnamnetAllUrs.size());
        //4. dantri
        HashSet<String> dantriUrls = urlManager.getDantriUrls("https://dantri.com.vn/xa-hoi.htm");
        System.out.printf("có %d link từ dantri\n", dantriUrls.size());
        //5. zing
        HashSet<String> zingUrls = urlManager.getZingUrls("https://zingnews.vn/giai-tri.html");
        System.out.printf("có %d link từ zing\n", zingUrls.size());
        /**** CREATE ARRAYLIST TO STORE THREADS ****/
        //1. kenh 14
        ArrayList<Thread> kenh14Threads = new ArrayList<Thread>();
        //2. dantri
        ArrayList<Thread> dantriThreads = new ArrayList<Thread>();
        //3. vnexpress
        ArrayList<Thread> vnexpressThreads = new ArrayList<Thread>();
        //4. vietnamnet
        ArrayList<Thread> vietnamnetThreads = new ArrayList<Thread>();
        //5. zing
        ArrayList<Thread> zingThreads = new ArrayList<Thread>();
        /**** start all thread ****/
        try {
            //1. kenh14
            for (String url : kenh14AllUrs) {
                //khoi tao thread
                Kenh14CrawlerThread kenh14CrawlerThread = new Kenh14CrawlerThread(url);
                //them thread vao list
                kenh14Threads.add(kenh14CrawlerThread);
                //start
                kenh14CrawlerThread.start();
            }
            //join de tinh time chay
            for (Thread kenh14Thread : kenh14Threads) {
                kenh14Thread.join();
            }

            //2. vnexpresss
            for (String url : vnExpressAllUrs) {
                //khoi tao thread
                VnexpressCrawlerThread vnexpressCrawlerThread = new VnexpressCrawlerThread(url);
                //them thread vao list
                vnexpressThreads.add(vnexpressCrawlerThread);
                //start
                vnexpressCrawlerThread.start();
            }
            //join de tinh time chay
            for (Thread vnexpressThread : vnexpressThreads) {
                vnexpressThread.join();
            }

            //3. vietnamnet
            for (String url : vietnamnetAllUrs) {
                //khoi tao thread
                VietnamnetCrawlerThread vietnamnetCrawlerThread = new VietnamnetCrawlerThread(url);
                //them thread vao list
                vietnamnetThreads.add(vietnamnetCrawlerThread);
                //start
                vietnamnetCrawlerThread.start();
            }
            //join de tinh time chay
            for (Thread vietnamnetThread : vietnamnetThreads) {
                vietnamnetThread.join();
            }

            //4. dantri
            for (String url : dantriUrls) {
                //khoi tao thread
                DantriCrawlerThread dantriCrawlerThread = new DantriCrawlerThread(url);
                //them thread vao list
                dantriThreads.add(dantriCrawlerThread);
                //start
                dantriCrawlerThread.start();
            }
            //join de tinh time chay
            for (Thread dantriThread : dantriThreads) {
                dantriThread.join();
            }

            //5. zing
            for (String url : zingUrls) {
                //khoi tao thread
                ZingCrawlerThread zingCrawlerThread = new ZingCrawlerThread(url);
                //them thread vao list
                zingThreads.add(zingCrawlerThread);
                //start
                zingCrawlerThread.start();
            }
            //join de tinh time chay
            for (Thread zingThread : zingThreads) {
                zingThread.join();
            }
            System.out.printf("có tổng cộng %d link báo !!!\n",
                    zingThreads.size() + vnexpressThreads.size() + dantriThreads.size() + vietnamnetThreads.size()
                            + kenh14Threads.size());
        } catch (InterruptedException e) {
            System.err.println("lỗi thread: " + e.getLocalizedMessage());
        }
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("thời gian chạy toàn bộ chương trình: " + (end - start) + " ms");
    }
}
