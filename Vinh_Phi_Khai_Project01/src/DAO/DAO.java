/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Library.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 * @param <M>
 */
public abstract class DAO<M> {

    PreparedStatement prepare = null;
    ResultSet rs = null;

    public abstract M getModel() throws SQLException;

    public List<M> executeQuery(String sql, Object... ob) {
        List<M> list = new ArrayList<>();
        try {
            rs = ConnectionDB.resultQuery(sql, ob);
            while (rs.next()) {
                list.add(getModel());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnectionDB.closeConnect(rs, (PreparedStatement) rs.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean executeUpdate(String sql, Object... ob) {
        try {
            prepare = ConnectionDB.prepareUpdate(sql, ob);
            if (prepare.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDB.closeConnect(null, prepare);
        }
        return false;
    }

}
