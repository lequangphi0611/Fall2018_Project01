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

    public static Connection openConnect() throws SQLException {
        return DriverManager.getConnection(ConnectInfo.URL, ConnectInfo.USER, ConnectInfo.PASS);
    }

    public static PreparedStatement prepareExecuted(String sql, Object... ob) throws SQLException {
        PreparedStatement prepare = openConnect().prepareStatement(sql);
        if (ob.length > 0) {
            for (int i = 0; i < ob.length; i++) {
                prepare.setObject(i + 1, ob[i]);
            }
        }
        return prepare;
    }

    public static ResultSet resultExeQuery(String sql, Object... ob) throws SQLException {
        PreparedStatement prepare = prepareExecuted(sql, ob);
        return prepare.executeQuery();
    }

    public static void closeConnect(ResultSet rs, PreparedStatement prepare) {
        Connection conn = null;
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (prepare != null && !prepare.isClosed()) {
                conn = prepare.getConnection();
                prepare.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
