package helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvertHelper {
    public static java.util.Date convertVnExpressDate(String stringDate) {
        //stringDate when crawl from vnexpress: Thứ năm, 30/4/2020, 19:16 (GMT+7)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // reformat dateString like pattern
        String[] splited = stringDate.split(",");
        String date = splited[1].trim();
        String hours = splited[2].replace("(GMT+7)", "").trim();
        String reformatDateString = date + " " + hours;
        //convert
        try {
            java.util.Date dateObject = simpleDateFormat.parse(reformatDateString);
            return dateObject;
        } catch (ParseException e) {
//            e.printStackTrace();
            System.err.println("Lỗi khi convert ngày: " + e.getMessage());
            return null;
        }

    }

    public static java.util.Date convertDantriDate(String dateString) {
        //string formate date look like: Thứ Ba 28/04/2020 - 07:45
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String[] dataSplit = dateString.split(" ");
        //get date
        String date = dataSplit[2].trim();
        //get hours
        String hours = dataSplit[4].trim();
        //reformat
        String reformatDateString = date + " " + hours;
        //parse
        try {
            java.util.Date dateObject = simpleDateFormat.parse(reformatDateString);
            return dateObject;
        } catch (ParseException e) {
//            e.printStackTrace();
            System.err.printf("lỗi khi convert %s lỗi %s\n", dateString, e.getMessage());
            return null;
        }
    }

    public static java.util.Date convertKenh14Date(String dateString) {
        //dateString looks like : 09:08 02/05/2020
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            java.util.Date dateObject = simpleDateFormat.parse(dateString);
            return dateObject;
        } catch (ParseException e) {
//            e.printStackTrace();
            System.err.println("convert date thất bại lỗi: " + e.getMessage());
            return null;
        }
    }

    public static java.util.Date convertVietnamnetDate(String dateString) {
        //datestring looks like: 01/05/2020 11:08 GMT+7
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String reformattedDateString = dateString.replace("GMT+7", "").trim();
        try {
            java.util.Date dateObject = simpleDateFormat.parse(reformattedDateString);
            return dateObject;
        } catch (ParseException e) {
//            e.printStackTrace();
            System.err.println("format date thất bại lỗi là: " + e.getMessage());
            return null;
        }
    }

    public static java.util.Date convertZingnewsDate(String dateString) {
        //dateString looks like: 05:50 02/05/2020
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            java.util.Date dateObject = simpleDateFormat.parse(dateString);
            return dateObject;
        } catch (ParseException e) {
//            e.printStackTrace();
            System.err.println("lỗi khi format datetime " + e.getMessage());
            return null;
        }
    }
}
