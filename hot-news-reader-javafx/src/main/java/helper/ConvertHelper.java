package helper;

import java.util.Date;

public class ConvertHelper {
    public static Date convertSqlTimeStampToJavaDate(java.sql.Timestamp timestamp) {
        Date javaDate = new Date(timestamp.getTime());
        return javaDate;
    }
}
