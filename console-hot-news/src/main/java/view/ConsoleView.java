package view;

import entity.Article;
import model.DatabaseModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    private static Scanner scanner;
    private static SimpleDateFormat simpleDateFormat;

    public void generateMenu() {

        System.out.println("------------------------------------- Chương trình đọc tin mới -------------------------------------");
        System.out.println("--------------------------------------------------------------------------viết bởi: Huy - 03/05/2020");
        while (true) {
            int categoryId = printCategoryMenu();
            if (categoryId == 6) {
                System.out.println("Thoát chương trình... cảm ơn bạn đã sử dụng.");
                break;
            }
            ArrayList<Article> topTenNews = printTenNewsByCategory(categoryId);
            //doc tin hay quay lai menu danh muc
            System.out.println("Bạn có muốn quay lại menu danh mục?");
            System.out.println("1. Không, tôi muốn đọc tin.");
            System.out.println("2. Có, hãy quay lại menu danh mục.");
            System.out.println("Hãy nhập lựa chọn của bạn (1 hoặc 2): ");
            int ans = 0;
            //valdidate
            while (true) {
                try {
                    ans = scanner.nextInt();
                } catch (Exception e) {
                    System.err.println("Thông tin không đúng!!! Chỉ chấp nhận 1 hoặc 2");
                    continue;
                } finally {
                    scanner.nextLine();
                }
                if (ans >= 1 && ans <= 2) {
                    break;
                } else {
                    System.err.println("Chỉ nhập 1 hoặc 2!!");
                }
            } //end while true for prompt anns
            if (ans == 2) {
                System.out.println("Quay lại menu danh mục...");
                continue;
            } else {
                while (true) {
                    Article chosenArticle = getChosenArticle(topTenNews);
                    chosenArticle.printArticleDetails(); //in ra tin chi tiet
                    //hein thi menu điều hướng
                    System.out.println("1. Trở lại danh sách tin"); //thì continue vòng lặp này
                    System.out.println("2. Trở về menu danh mục"); //thì break vòng lặp này
                    System.out.println("3. Thoát chương trình");  //thì system.exit
                    System.out.println("Nhập lựa chọn của bạn (từ 1 đến 3): ");
                    int userAction = 0;
                    //validate
                    while (true) {
                        try {
                            userAction = scanner.nextInt();
                        } catch (Exception e) {
                            System.err.println("Thông tin không đúng, chỉ chấp nhận số từ 1 đến 3!!!");
                            continue;
                        } finally {
                            scanner.nextLine();
                        }
                        if (userAction >= 1 && userAction <= 3) {
                            break;
                        } else {
                            System.err.println("Chỉ nhập từ 1 đến 3!!!");
                        }
                    } //end while true for validation
                    if (userAction == 1) {
                        System.out.println("Quay lại danh sách tin...");
                        //in lại danh mục tin dạnh bảng
                        System.out.println("------------------------------------------------------------------------------------" +
                                "------------------------------- TOP 10 tin mới nhất ----------------------------------------------" +
                                "-------------------------------------------------------------------");
                        System.out.printf("%5s %-5s %5s %s %5s %-150s %5s %s %5s %-20s %5s %s %5s %-15s %5s %s\n",
                                "", "Stt", "", "|"
                                , "", "Tiêu đề", "", "|"
                                , "", "Ngày xuất bản", "", "|"
                                , "", "Nguồn", "", "|");
                        System.out.println("----------------------------------------------------------------------------------------" +
                                "--------------------------------------------------------------------------------------------------" +
                                "---------------------------------------------------------------");
                        for (int i = 0; i < topTenNews.size(); i++) {
                            Article article = topTenNews.get(i);
                            System.out.printf("%5s %-5s %5s %s %5s %-150s %5s %s %5s %-20s %5s %s %5s %-15s %5s %s\n",
                                    "", Integer.toString(i + 1), "", "|"
                                    , "", article.getTitle(), "", "|"
                                    , "", simpleDateFormat.format(article.getPublicDate()), "", "|"
                                    , "", article.getSource(), "", "|");
                        }
                        System.out.println("----------------------------------------------------------------------------------------" +
                                "--------------------------------------------------------------------------------------------------" +
                                "---------------------------------------------------------------");
                        //continue vòng lặp ở dòng 50
                        continue;
                    } else if (userAction == 2) {
                        System.out.println("Trở về menu dnah mục...");
                        //break vòng lặp dòng 50 để vòng lặp dòng 17 (menu danh mục) tiếp tục chạy
                        break;
                    } else {
                        System.out.println("thoát chương trình... cảm ơn bạn đã sử dụng");
                        //exit toàn bộ chương trình
                        System.exit(-1);
                    }
                } //end while true for read 10 news
            } //end if else
        } //category loop
    }

    //print category menu and return number that user choose
    private static int printCategoryMenu() {
        System.out.println("----------------------- Menu danh mục -----------------------");
        System.out.println("1. Tin chính trị");
        System.out.println("2. Tin xã hội");
        System.out.println("3. Tin thời sự");
        System.out.println("4. Tin giải trí");
        System.out.println("5. Tin scandal");
        System.out.println("6. thoát");
        System.out.println("Hãy nhập từ 1 đến 6 để chọn");
        int categoryId = 0;
        while (true) {
            try {
                categoryId = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("chỉ chấp nhận số từ 1 đến 6 hãy nhập lại");
                continue;
            } finally {
                scanner.nextLine();
            }
            if (categoryId >= 1 && categoryId <= 6) {
                break;
            } else {
                System.err.println("Hãy nhập một số từ 1 đến 6");
            }
        }
        return categoryId;
    }

    private static ArrayList<Article> printTenNewsByCategory(int categoryId) {
        //get arraylist of top ten articles then print title public date and source as table format
        //then return arraylist of top ten news
        DatabaseModel databaseModel = new DatabaseModel();
        ArrayList<Article> topTenNews = databaseModel.getTopTenNewsByCategory(categoryId);

        //print data as table
        System.out.println("------------------------------------------------------------------------------------" +
                "------------------------------- TOP 10 tin mới nhất ----------------------------------------------" +
                "-------------------------------------------------------------------");
        System.out.printf("%5s %-5s %5s %s %5s %-150s %5s %s %5s %-20s %5s %s %5s %-15s %5s %s\n",
                "", "Stt", "", "|"
                , "", "Tiêu đề", "", "|"
                , "", "Ngày xuất bản", "", "|"
                , "", "Nguồn", "", "|");
        System.out.println("----------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------" +
                "---------------------------------------------------------------");
        for (int i = 0; i < topTenNews.size(); i++) {
            Article article = topTenNews.get(i);
            System.out.printf("%5s %-5s %5s %s %5s %-150s %5s %s %5s %-20s %5s %s %5s %-15s %5s %s\n",
                    "", Integer.toString(i + 1), "", "|"
                    , "", article.getTitle(), "", "|"
                    , "", simpleDateFormat.format(article.getPublicDate()), "", "|"
                    , "", article.getSource(), "", "|");
        }
        System.out.println("----------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------" +
                "---------------------------------------------------------------");
        return topTenNews;
    }

    private static Article getChosenArticle(ArrayList<Article> topTenArticle) {
        System.out.println("Bạn muốn đọc tin nào ? Hãy nhập từ 1 đến 10.");
        int newsIndex = 0;
        while (true) {
            try {
                newsIndex = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("chỉ chấp nhận số từ 1 đến 10... Hãy nhập lại");
                continue;
            } finally {
                scanner.nextLine();
            }
            if (newsIndex >= 1 && newsIndex <= 10) {
                break;
            } else {
                System.err.println("Hãy nhập một số từ 1 đến 10");
            }
        }
        return topTenArticle.get(newsIndex - 1);
    }

    public ConsoleView() {
        scanner = new Scanner(System.in);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    }
}
