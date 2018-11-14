/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Library.ConnectionDB;
import Library.Convert;
import Library.MyLibry;
import Model.Bill;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class BillDAO extends DAO<Bill> {

    @Override
    public Bill getModel() throws SQLException {
        return new Bill(
                rs.getString("IdBill"),
                rs.getString("IdEmployees"),
                rs.getTime("TimePayment"),
                rs.getDate("DatePayment"),
                rs.getInt("TableNumber"),
                rs.getLong("SumPrice"),
                rs.getLong("Sale")
        );
    }

    public List<Bill> getAll() {
        return executeQuery("SELECT * FROM bill order by DatePayment desc, TimePayment desc");
    }

    public boolean insert(Bill model) {
        long milisecond = model.getDatePayment().getTime();
        return executeUpdate(
                "insert into bill "
                + "(IdBill,IdEmployees,TimePayment,DatePayment,TableNumber,SumPrice,Sale,Total) "
                + "values (?,?,?,?,?,?,?,?)",
                model.getIdBill(),
                model.getIdEmployees(),
                new java.sql.Time(milisecond),
                new java.sql.Date(milisecond),
                model.getTableNumber(),
                model.getSumPrice(),
                model.getSale(),
                model.getTotal()
        );
    }

    public List<Bill> findModel(String id) {
        return executeQuery("Select * from bill where IdBill = ?", id);
    }

    public String getIDBill() {
        while (true) {
            String idBill = MyLibry.getRandomIdBill(10);
            if (findModel(idBill).isEmpty()) {
                return idBill;
            }
        }
    }

    public String minDate() {
        try {
            rs = ConnectionDB.resultQuery("select min(DatePayment) from bill ");
            rs.next();
            return Convert.formatDate(rs.getDate(1), "dd/MM/yyyy");
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String maxDate() {
        try {
            rs = ConnectionDB.resultQuery("select max(DatePayment) from bill ");
            rs.next();
            return Convert.formatDate(rs.getDate(1), "dd/MM/yyyy");
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
