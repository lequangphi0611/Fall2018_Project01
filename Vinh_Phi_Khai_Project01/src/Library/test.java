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
import Model.ContentBill;
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
        

    }

    static void testTien() {

        ItemOrder[] order = new ItemOrder[5];
        order[0] = new ItemOrder(1, "Sting dâu", 7000, 2, 5);
        order[1] = new ItemOrder(2, "Huda", 11000, 2, 24);
        order[2] = new ItemOrder(3, "Dê nướng", 50000, 1, 2);
        order[3] = new ItemOrder(4, "Sà lách", 1000, 1, 6);
        order[4] = new ItemOrder(5, "Nước súi", 8000, 2, 8);
        List<ItemOrder> list = new ArrayList<>();
        list.addAll(Arrays.asList(order));
        Table tb01 = new Table(1);
        tb01.setItemOrder(list);
        for (ItemOrder item : tb01.getItemOrder()) {
            System.out.println(item.toString());
            System.out.println("Tổng tiền mỗi item = " + item.sumPrice());
        }
        System.out.println("Tổng tiền của bàn = " + tb01.sumPrice());
        Table tb02 = new Table(3);
        System.out.println(tb01.getTableName() + " " + tb02.getTableName());
    }

}
