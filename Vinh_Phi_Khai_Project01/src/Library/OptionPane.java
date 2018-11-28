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
public class OptionPane {

    public static final String TILTLE = "HỆ THỐNG QUẢN LÝ NHÀ HÀNG";

    public static boolean error(Component combonent, String message) {
        combonent.requestFocus();
        alert(null, message);
        return false;
    }

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, TILTLE, JOptionPane.ERROR_MESSAGE);
    }

    public static void success(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, TILTLE, JOptionPane.DEFAULT_OPTION);
    }

    public static boolean confirm(Component parent, String message) {
        int confirm = JOptionPane.showConfirmDialog(parent, message, TILTLE, JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }
    
}
