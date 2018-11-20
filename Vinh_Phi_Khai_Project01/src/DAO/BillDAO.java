/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Library.ConnectionDB;
import Library.MyLibry;
import Model.Bill;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            String idBill = MyLibry.getRandomText("HD",MyLibry.ALANDNUM,10);
            if (findModel(idBill).isEmpty()) {
                return idBill;
            }
        }
    }

    public Date minDate() {
        return getMinMaxDate()[0];
    }

    public Date maxDate() {
        return getMinMaxDate()[1];
    }

    private Date[] getMinMaxDate() {
        try {
            rs = ConnectionDB.resultQuery("select min(DatePayment),max(DatePayment) from bill");
            if (rs.next()) {
                Date minDate = rs.getDate(1);
                Date maxDate = rs.getDate(2);
                if (minDate != null && maxDate != null) {
                    return new Date[]{minDate, maxDate};
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnectionDB.closeConnect(rs, (PreparedStatement) rs.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Date[]{new Date(),new Date()};
    }

    public List<Bill> getListForDate(Date dateBefore, Date dateAfter) {
        return executeQuery(
                "exec sp_LichSuGiaoDich ?, ?",
                new java.sql.Date(dateBefore.getTime()),
                new java.sql.Date(dateAfter.getTime())
        );
    }

}
