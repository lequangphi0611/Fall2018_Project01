/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Model.Item;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class ItemDAO extends DAO<Item> implements IDao<Item, Integer> {

    @Override
    public Item getModel() throws SQLException {
        return new Item(
                rs.getInt("IdItem"),
                rs.getString("ItemName"),
                rs.getLong("Price"),
                rs.getInt("IdCategory")
        );
    }

    @Override
    public List<Item> getAll() {
        return executeQuery("select * from Item");
    }

    @Override
    public boolean insert(Item model) {
        return executeUpdate("insert into Item (ItemName,Price,IdCategory)"
                + "values (?,?,?)",
                model.getItemName(),
                model.getPrice(),
                model.getIdCategory()
        );
    }

    @Override
    public boolean update(Item model) {
        return executeUpdate("update Item set ItemName = ?, Price = ?, IdCategory = ?"
                + " where IdItem = ?", 
                model.getItemName(),
                model.getPrice(),
                model.getIdCategory(),
                model.getIdItem()
        );
    }

    @Override
    public boolean delete(Integer object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> findModel(Integer object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
