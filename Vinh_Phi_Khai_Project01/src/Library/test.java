/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import DAO.CategoryDAO;
import DAO.EmployeesDAO;
import DAO.ItemDAO;
import DAO.UserDAO;
import Model.Category;
import Model.Employees;
import Model.Item;
import Model.ItemOrder;
import Model.Table;
import Model.Users;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        testTable();
        
    }
    
    static void testTable(){
        int i  = 1;
        List<ItemOrder> item = new ArrayList<>();
        item.add(new ItemOrder(i, "Dầu gội đầu", 60000, i, i++));
        item.add(new ItemOrder(i, "Vegetable", 120000, i, i++));
        item.add(new ItemOrder(i, "chả biết", 80000, i, i++));
        item.add(new ItemOrder(i, "thức ăn", 222000, i, i++));
        item.add(new ItemOrder(i, "thuốc", 10000, i, i++));
        
        Table table = new Table(2,item);
        System.out.println(table.getTableNameString());
        System.out.println(table.statusToString());
        for(i = 0; i < table.getItemOrder().size(); i++){
            ItemOrder items = table.getItemOrder().get(i);
            System.out.println("ID = "+items.getIdItem() + 
                    ", Tến sản phẩm = "+items.getItemName() + 
                    ", Giá tiền = "+items.getPrice() + " , số lượng = "+ items.getQuantity());
            System.out.println("Tổng giá sản phẩm : "+items.sumPrice());
        }
        System.out.println("Tổng giá của bàn : "+table.sumPrice());
    }
    
}
