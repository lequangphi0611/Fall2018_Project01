/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static Connection openConnect()throws SQLException{
        return DriverManager.getConnection(ConnectInfo.URL, ConnectInfo.USER, ConnectInfo.PASS);
    }
    
    public static void closeConnect(ResultSet rs,PreparedStatement prepare,Connection conn){
        try {
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(prepare != null && !prepare.isClosed()){
                prepare.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
