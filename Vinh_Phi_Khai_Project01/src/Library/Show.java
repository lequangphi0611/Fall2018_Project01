/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Quang Phi
 */
public class Show {
 
    public static final String TILTLE = "HỆ THỐNG QUẢN LÝ NHÀ HÀNG";
    
    public static boolean error(Component combonent,String message){
        combonent.requestFocus();
        alert(combonent, message);
        return false;
    }
    
    public static void alert(Component parent,String message){
        JOptionPane.showMessageDialog(parent,message, TILTLE,  JOptionPane.ERROR_MESSAGE);
    }
    
    public static void success(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, TILTLE,-1);
    }
    
    public static boolean confirm(Component parent, String message){
        int confirm = JOptionPane.showConfirmDialog(parent, message, TILTLE, JOptionPane.QUESTION_MESSAGE);
        return confirm == JOptionPane.YES_OPTION;
    }
    
}
