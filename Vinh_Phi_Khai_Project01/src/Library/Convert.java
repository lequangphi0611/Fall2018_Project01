/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Quang Phi
 */
public class Convert {

    private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("###,###,###");

    public static String toMoney(long money) {
        return FORMATDECIMAL.format(money) + " đ";
    }

    public static Date getNow() {
        return new Date();
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parseDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException ex) {
            throw new Error("Không thể định dạng ngày !");
        }
    }
    
    public static Date addDate(Date dateInPut, int day){
        long milisecond = dateInPut.getTime() + (day * 24 * 60 * 60 * 1000);
        return new Date(milisecond);
    }

}
