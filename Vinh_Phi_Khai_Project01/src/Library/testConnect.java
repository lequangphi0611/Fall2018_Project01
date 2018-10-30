/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class testConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnect();
            if(conn != null){
                System.out.println("Connected");
            }else{
                System.out.println("Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(testConnect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDB.closeConnect(null, null, conn);
        }
    }
    
}
