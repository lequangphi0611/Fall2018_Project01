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
    
    public List<Item> getItemIsSell(){
        List<Item> list = getAll();
        for(int i = 0; i < list.size();){
            Item item = list.get(i);
            if(!item.isSell()){
                list.remove(i);
            }else{
                i++;
            }
        }
        return list;
    }
    
    public List<Item> getItemStopSell(){
        List<Item> list = getAll();
        for(int i = 0; i < list.size();){
            Item item = list.get(i);
            if(item.isSell()){
                list.remove(i);
            }else{
                i++;
            }
        }
        return list;
    }

    //Kiểm tra xem mặt hàng này đã được bán chưa
    public boolean checkItemInBill(int id){
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
        );
    }

    @Override
    public boolean update(Item model) {
        return executeUpdate("update Item set ItemName = ?,Unit = ?, Price = ?, IdCategory = ? , isSell = ?"
                + " where IdItem = ?",
                model.getItemName(),
                model.getUnit(),
                model.getPrice(),
                model.getIdCategory(),
                model.isSell() ? 1 : 0,
                model.getIdItem()
        );
    }

    @Override
    public boolean delete(Integer object) {
        return executeUpdate("delete from Item where IdItem = ?", object);
    }

    @Override
    public List<Item> findModel(Integer object) {
        return executeQuery("Select * from Item where IdItem = ?", object);
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
