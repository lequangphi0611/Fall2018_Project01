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

    private static final SimpleDateFormat FORMATDATE = new SimpleDateFormat("dd/MM/yyyy");
    private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("###,###,###");

    public static String toMoney(long money, String... pattern) {
        if (pattern.length > 0) {
            return new DecimalFormat(pattern[0]).format(money);
        }
        return FORMATDECIMAL.format(money) + " đ";
    }

    public static Date getNow() {
        return new Date();
    }

    public static String formatDate(Date date, String... pattern) {
        if (pattern.length > 0) {
            return new SimpleDateFormat(pattern[0]).format(date);
        }
        return FORMATDATE.format(date);
    }

    public static Date parseDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                return new SimpleDateFormat(pattern[0]).parse(date);
            }
            return FORMATDATE.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
