package helper;

import java.util.Date;

public class ConvertHelper {
    public static java.util.Date convertSqlTimeStampToJavaDate(java.sql.Timestamp timestamp) {
        Date javaDate = new Date(timestamp.getTime());
        return javaDate;
    }
}
