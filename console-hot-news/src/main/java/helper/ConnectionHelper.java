package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hot-news-crawlers?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Bangkok&characterEncoding=utf-8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    private static Connection connection;
    public static Connection getConnectionHelper () {
        if(connection == null) {
            try {
                System.out.println("đang kết nối tới database....");
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                System.out.println("kết nối đến database thành công !!");
                return connection;
            } catch (SQLException e) {
                System.err.println("Kết nối đến database thất bại lỗi là: "+ e.getMessage());
                return null;
            }
        }
        return connection;
    }
}
