/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import javax.swing.text.JTextComponent;

/**
 *
 * @author Quang Phi
 * Thư viện kiểm một số lỗi cơ bản
 */
public class Error {
    
    public static final String NUMBER = "^[0-9]+$";
    public static final String UNICODE = "^[\\p{L}\\s]+$";
    public static final String ALPHABET = "^[a-zA-Z0-9_-]+$";

    
    public static boolean isEmpty(String text){
        return text.isEmpty();
    }
    //Kiểm tra trống một JText
    //Trống trả về true
    public static boolean isEmpty(JTextComponent component){
        return isEmpty(component.getText().trim());
    }
    
    public static boolean checkMatch(String text, String pattern){
        return text.matches(pattern);
    }
    
    public static boolean isNumber(String text){
        return checkMatch(text, NUMBER);
    }
    
    public static boolean isUnicode(String text){
        return checkMatch(text, UNICODE);
    }
    
    public static boolean isAlphabet(String text){
        return checkMatch(text, ALPHABET);
    }
    
}
