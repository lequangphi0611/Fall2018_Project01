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

    static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY";
    static final Random RD = new Random();
    static final char[] CHARS = ALPHABET.toCharArray();

    public static String randomString(int length) {
        String result = "";
        int lengthChars = CHARS.length;
        for (int i = 0; i < length; i++) {
            result += CHARS[RD.nextInt(lengthChars)];
        }
        return result;
    }

}
