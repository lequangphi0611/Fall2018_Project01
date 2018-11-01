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
public class Stringlibry {

    static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY";
    static final Random RD = new Random();
    static final char[] CHARS = ALPHABET.toCharArray();

    public static String randomString(int length) {
        String result = "";
        for (int i = 0; i < length;) {
            int index = RD.nextInt(CHARS.length);
            result += CHARS[index];
            i++;
        }
        return result;
    }

    public static String[] stringToArray(String str, String chr) {
        String[] first = new String[str.length()];
        int i = 0;
        while (true) {
            int index = str.indexOf(chr);
            if (index < 0) {
                first[i] = str.substring(0).trim();
                break;
            } else if (index == 0) {
                str = str.substring(1);
            } else{
                first[i] = str.substring(0, index).trim();
                str = str.substring(index);
                i++;
            }
        }

        String[] result = new String[++i];
        System.arraycopy(first, 0, result, 0, result.length);
        return result;
    }

    public static String arrayToString(String[] str, String chr) {
        String result = "";
        for(String str1 : str){
            result += str1 + chr;
        }
        return result;
    }

}
