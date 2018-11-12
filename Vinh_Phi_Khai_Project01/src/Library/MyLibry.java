/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.Random;

/**
 *
 * @author Quang Phi
 */
public class MyLibry {

    private static final String FIRSTBILL = "HD";
    private static final Random RD = new Random();
    private static final String ALANDNUM = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomIdBill(int length) {
        String result = FIRSTBILL;
        if (length > result.length()) {
            length -= result.length();
            for (int i = 0; i < length; i++) {
                result += ALANDNUM.charAt(RD.nextInt(ALANDNUM.length()));
            }
        }
        return result;
    }

}
