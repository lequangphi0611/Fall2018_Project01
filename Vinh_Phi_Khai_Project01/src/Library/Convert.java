/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.text.DecimalFormat;

/**
 *
 * @author Quang Phi
 */
public class Convert {

    public static String[] stringToArray(String str, char chr) {
        String[] first = new String[str.length()];
        int i = 0;
        while (true) {
            int index = str.indexOf(chr);
            if (index == 0) {
                str = str.substring(1);
            } else if (index > 0) {
                first[i] = str.substring(0, index).trim();
                str = str.substring(index);
                i++;
            } else if (index < 0) {
                first[i] = str.substring(0).trim();
                break;
            }
        }

        String[] result = new String[++i];
        System.arraycopy(first, 0, result, 0, result.length);
        return result;
    }

    public static String toMoney(double money, String... pattern) {
        DecimalFormat format = new DecimalFormat("###,###,###.##");
        if (pattern.length > 0) {
            format.applyPattern(pattern[0]);
        }
        return format.format(money) + " đ";
    }

}
