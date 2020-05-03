package model;

import entity.Article;
import helper.ConnectionHelper;
import helper.ConvertHelper;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseModel {
    public ArrayList<Article> getTopTenNewsByCategory(int categoryId) {
        // categoryId: 1. Chính trị | 2. Xã Hội | 3. thời sự | 4. giải trí | 5. scandal
        ArrayList<Article> topTenNews = new ArrayList<Article>();
        Connection connection = ConnectionHelper.getConnectionHelper();
        String queryString = "";
        switch (categoryId) {
            case 1: // chính trị
                System.out.println("đang lấy tin với danh mục chính trị....");
                queryString ="SELECT * FROM articles WHERE category = 'Chính trị' ORDER BY public_date DESC LIMIT 10;";
                break;
            case 2: // xã hội
                System.out.println("đang lấy tin với danh mục xã hội....");
                queryString = "SELECT * FROM articles WHERE category = 'Xã Hội' ORDER BY public_date DESC LIMIT 10;";
                break;
            case 3 : //thời sự
                System.out.println("đang lấy tin với danh mục thời sự....");
                queryString ="SELECT * FROM articles WHERE category = 'thời sự' ORDER BY public_date DESC LIMIT 10;";
                break;
            case 4: //giải trí
                System.out.println("đang lấy tin với danh mục giải trí....");
                queryString = "SELECT * FROM articles WHERE category = 'Giải trí' ORDER BY public_date DESC LIMIT 10;";
                break;
            case 5: // scandal
                System.out.println("đang lấy tin với danh mục scandal....");
                queryString ="SELECT * FROM articles " +
                        "WHERE category = 'star' OR category = 'Sao việt' ORDER BY public_date DESC LIMIT 10;";
                break;
            default:
                queryString = "";
                System.out.println("categoryId không hợp lệ");
                break;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                String category = resultSet.getString("category");
                String url = resultSet.getString("url");
                String source = resultSet.getString("source");
                Timestamp timestampPublicDate = resultSet.getTimestamp("public_date");
                //convert to java.util.Date
                java.util.Date publicDate = ConvertHelper.convertSqlTimeStampToJavaDate(timestampPublicDate);
                //create an instance of Article class for each result set
                Article article = new Article(title, description, content, category, publicDate, url, source);
                //add to arraylist
                topTenNews.add(article);

            }
            System.out.println("lấy tin thành công !");
        } catch (SQLException e) {
            System.err.println("lấy tin từ database thất bại, lỗi: " + e.getMessage());
        }
        return topTenNews;
    }

}
