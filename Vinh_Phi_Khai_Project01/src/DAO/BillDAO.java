/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Library.MyLibry;
import Model.Bill;
import java.sql.SQLException;
import java.util.List;

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
                rs.getDate("DatePayment"),
                rs.getInt("TableNumber"),
                rs.getLong("SumPrice"),
                rs.getLong("Sale")
        );
    }

    public List<Bill> getAll() {
        return executeQuery("SELECT * FROM bill");
    }

    public boolean insert(Bill model) {
        return executeUpdate(
                "insert into bill "
                + "(IdBill,IdEmployees,DatePayment,TableNumber,SumPrice,Sale,Total) "
                + "values (?,?,?,?,?,?,?)",
                model.getIdBill(),
                model.getIdEmployees(),
                new java.sql.Date(model.getDatePayment().getTime()),
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
            if(findModel(idBill).isEmpty()){
                return idBill;
            }
        }
    }

}
