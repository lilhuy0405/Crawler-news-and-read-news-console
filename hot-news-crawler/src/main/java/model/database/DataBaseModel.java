package model.database;

import entity.Article;
import helper.ConnectionHelper;

import java.sql.*;

public class DataBaseModel {
    public void insertArticleToDatabase(Article article) {
        if (article != null) {

            try {
                Connection connection = ConnectionHelper.getDataBaseConnection();
                String statement = "INSERT INTO `articles` (`title`, `description`, `content`, `public_date`, `category`, `source`, `url`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setString(1, article.getTitle());
                preparedStatement.setString(2, article.getDescription());
                preparedStatement.setString(3, article.getContent());
                //convert java date to sql timestamp
                Timestamp publicDate = new Timestamp(article.getPublicDate().getTime());
                preparedStatement.setTimestamp(4, publicDate);
                preparedStatement.setString(5, article.getCategory());
                preparedStatement.setString(6, article.getSource());
                preparedStatement.setString(7, article.getUrl());
                preparedStatement.execute();
                System.out.printf("đã thêm article %s vào database thành công\n", article.getTitle());
            } catch (SQLException throwable) {
//            throwable.printStackTrace();
                System.err.println("lỗi khi thao tác với database lỗi là + " + throwable.getMessage());
            }
        }
        return;
    }
}
