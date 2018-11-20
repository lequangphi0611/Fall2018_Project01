/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.BillDetail;
import Model.Ware;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class BillDetailDAO extends DAO<BillDetail> {
    
    WareDAO ware = new WareDAO();

    @Override
    public BillDetail getModel() throws SQLException {
        return new BillDetail(
                rs.getString("IdBill"),
                rs.getInt("IdItem"),
                rs.getInt("Quantity"),
                rs.getLong("Price")
        );
    }

    public List<BillDetail> getAllOfBill(String IdBill) {
        return executeQuery("Select * from BillDetail where IdBill = ? order by (Price * Quantity) asc", IdBill);
    }

    public boolean insert(BillDetail model) {
        return executeUpdate(
                "insert into BillDetail(IdBill,IdItem,Quantity,Price)"
                + "values (?,?,?,?)",
                model.getIdBill(),
                model.getIdItem(),
                model.getQuantity(),
                model.getPrice()
        );
    }

}
