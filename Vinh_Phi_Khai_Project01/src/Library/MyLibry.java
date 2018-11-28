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
    private static final String ALANDNUM = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static char getRamDomCharAlpabet() {
        return ALANDNUM.charAt(RD.nextInt(ALANDNUM.length()));
    }
    public static String getRandomText(int length) {
        String result = "";
        for(int i = 0; i < length; i++){
            result += getRamDomCharAlpabet();
        }
        return result;
    }

}
