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

    private static final Random RD = new Random();
    public static final String ALANDNUM = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int getRamDom(int length) {
        return RD.nextInt(length);
    }

    private static char getCharRanDomTo(String str) {
        return str.charAt(getRamDom(str.length()));
    }

    public static String getRandomText(String firstText, String ranDomString, int length) {
        String result = firstText;
        if (result != null && length > result.length()) {
            length -= result.length();
        }
        for (int i = 0; i < length; i++) {
            result += getCharRanDomTo(ranDomString);
        }
        return result;
    }

}
