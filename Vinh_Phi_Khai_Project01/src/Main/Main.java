
package Main;

import Data.UserData;
import Form.*;
import Library.OptionPane;

/**
 *
 * @author Quang Phi
 */
public class Main {

    public static void loginAndRunMainSystem(MainJFrame frame) {
        new LogInJFrame(null, true).setVisible(true);
        if (UserData.isLogin()) {
            frame.setVisible(true);
        } else {
            turnOffSystem(frame);
        }
    }

    public static void turnOffSystem(MainJFrame frame) {
        if (OptionPane.confirm(null, "Bạn có muốn thoát chương trình !")) {
            System.exit(0);
        } else if (!frame.isVisible()) {
            loginAndRunMainSystem(frame);
        }
    }

    public static void main(String[] args) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                loginAndRunMainSystem(new MainJFrame());
            }
        });
    }

}
