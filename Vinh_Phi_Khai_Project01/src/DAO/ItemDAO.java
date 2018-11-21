/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Model.Item;
import Model.Ware;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class ItemDAO extends DAO<Item> implements IDao<Item, Integer> {

    WareDAO wareDAO = new WareDAO();
    ImportItemDAO importDO = new ImportItemDAO();

    @Override
    public Item getModel() throws SQLException {
        return new Item(
                rs.getInt("IdItem"),
                rs.getString("ItemName"),
                rs.getString("Unit"),
                rs.getLong("Price"),
                rs.getString("IdCategory"),
                rs.getInt("isSell") != 0
        );
    }

    @Override
    public List<Item> getAll() {
        return executeQuery("select * from Item order by IdItem desc");
    }

    public List<Item> getItemIsSell() {
        return executeQuery("select * from Item where isSell=1");
    }

    public List<Item> getItemStopSell() {
        return executeQuery("select * from Item where isSell=0");
    }
    
    public List<Item> getListForOrder(){
        return executeQuery("select * from Item inner join Ware on Item.IdItem = Ware.IdItem "
                + "where Item.isSell = 1 and  Ware.QuantityRemain > 0");
    }
    
    public int itemInWare(int idItem){
        Ware ware = wareDAO.selectByItem(idItem);
        return ware.getQuantityRemain();
    }

    //Kiểm tra xem mặt hàng này đã được bán chưa
    public boolean checkItemInBill(int id) {
        return !executeQuery(
                "select * from Item inner join BillDetail "
                + "on Item.IdItem = BillDetail.IdItem where item.IdItem = ?",
                id
        ).isEmpty();
    }

    @Override
    public boolean insert(Item model) {
        return executeUpdate("insert into Item (ItemName,Unit,Price,IdCategory, isSell)"
                + "values (?,?,?,?,?)",
                model.getItemName(),
                model.getUnit(),
                model.getPrice(),
                model.getIdCategory(),
                model.isSell() ? 1 : 0
        ) && wareDAO.insert(model.getItemName());
    }

    @Override
    public boolean update(Item model) {
        return executeUpdate("update Item set ItemName = ?,Unit = ?, Price = ?, IdCategory = ?"
                + " where IdItem = ?",
                model.getItemName(),
                model.getUnit(),
                model.getPrice(),
                model.getIdCategory(),
                model.getIdItem()
        );
    }
    
    public void setSell(Boolean isSell){
        executeUpdate("update Item set isSell=?", isSell);
    }

    @Override
    public boolean delete(Integer object) {
        return executeUpdate("delete from Item where Item.IdItem = ?", object);
    }

    @Override
    public List<Item> findModel(Integer object) {
        return executeQuery("Select * from Item where IdItem = ?", object);
    }
    
    public List<Item> findModel(String itemName){
        return executeQuery("Select * from Item where ItemName=?", itemName);
    }

    public List<Item> getItemByCategory(String category) {
        return executeQuery(
                "select * from Item inner join Category on Item.IdCategory = "
                + "Category.IdCategory where Category.CategoryName = ? order by"
                + " Item.Price asc",
                category
        );
    }

}
