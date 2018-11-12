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

    public static String toMoney(double money, String... pattern) {
        DecimalFormat format = new DecimalFormat("###,###,###.##");
        if (pattern.length > 0) {
            format.applyPattern(pattern[0]);
        }
        return format.format(money) + " đ";
    }

    public static Date getNow() {
        return new Date();
    }

    public static String formatDate(Date date, String... pattern) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
        if (pattern.length > 0) {
            try {
                simpleDate.applyPattern(pattern[0]);
            } catch (java.lang.IllegalArgumentException ex) {
            }
        }
        return simpleDate.format(date);
    }
    
    public static Date parseDate(String date, String... pattern) throws ParseException{
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
        if(pattern.length > 0){
            try {
                simpleDate.applyPattern(pattern[0]);
            } catch (java.lang.IllegalArgumentException ex) {
            }
        }
        return simpleDate.parse(date);
    }
}
