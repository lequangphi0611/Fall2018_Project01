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

    private static final String FIRSTBILL = "HD0";
    private static final String NUM = "0123456789";//Bổ sung ký tự alphabet
    private static final Random RD = new Random();

    private static char[] getCharArr() {
        return NUM.toCharArray();
    }

    public static String getRandomIdBill(int length) {
        String result = FIRSTBILL;
        if (length > result.length()) {
            length -= result.length();
            char[] chr = getCharArr();
            int lengthOfChr = chr.length;
            for (int i = 0; i < length; i++) {
                int rdIndex = RD.nextInt(lengthOfChr);
                result += chr[rdIndex];
            }
        }
        return result;
    }

}
