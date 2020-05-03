package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    //database
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hot-news-crawlers?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Bangkok&characterEncoding=utf-8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    private static Connection connection;

    public static Connection getDataBaseConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (SQLException e) {
                System.out.println("kết nối tới database thất bại...Lỗi: " + e.getMessage());
                return null;
//                e.printStackTrace();
            }
        }
        return connection;
    }

}
