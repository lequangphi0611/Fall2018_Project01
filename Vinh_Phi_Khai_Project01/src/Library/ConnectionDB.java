/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class ConnectionDB {
    static {
        try {
            Class.forName(ConnectInfo.DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection openConnect(){
        return null;
    }
    public void closeConnect(){}
}
