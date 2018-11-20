/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Item;
import Model.Ware;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class WareDAO extends DAO<Ware> {

    @Override
    public Ware getModel() throws SQLException {
        return new Ware(
                rs.getInt("IdItem"),
                rs.getInt("QuantityRemain")
        );
    }
    
    public List<Ware> getAll(){
        return executeQuery("select * from Ware");
    }

    public boolean insert(int idItem) {
        return executeUpdate("insert into Ware(IdItem,QuantityRemain) values (?,0)", idItem);
    }
    
    public boolean insert(String itemName){
        Item item = new ItemDAO().findModel(itemName).get(0);
        return insert(item.getIdItem());
    }

    public Ware selectByItem(int IdItem) {
        List<Ware> list = executeQuery("select * from ware where IdItem=?", IdItem);
        return list.get(0);
    }

    public boolean update(Ware wh) {
        return executeUpdate(
                "update Ware set QuantityRemain=? where IdItem=?",
                wh.getQuantityRemain(),
                wh.getIdItem()
        );
    }
    
    public boolean addQuantityRemain(Ware wh) {
        Ware whLocal = selectByItem(wh.getIdItem());
        int quantityRemain = whLocal.getQuantityRemain();
        quantityRemain += wh.getQuantityRemain();
        whLocal.setQuantityRemain(quantityRemain);
        return update(whLocal);
    }

    public boolean subQuantityRemain(Ware wh){
        Ware whLocal = selectByItem(wh.getIdItem());
        int quantityRemain = whLocal.getQuantityRemain();
        if(quantityRemain >= wh.getQuantityRemain()){
            quantityRemain -= wh.getQuantityRemain();
            whLocal.setQuantityRemain(quantityRemain);
            return update(whLocal);
        }else{
            throw new Error("Dữ liệu truyền vào vượt quá số lượng của mặt hàng có trong kho");
        }
    }
    
}
