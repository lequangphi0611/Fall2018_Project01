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
import Model.Users;
import com.oracle.webservices.internal.api.databinding.DatabindingModeFeature;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        EmployeesDAO dao = new EmployeesDAO();
//        Employees[] arr = new Employees[5];
//        arr[0] = new Employees("EE001", "Phi", 19, true);
//        arr[1] = new Employees("EE002", "Khải", 19, true);
//        arr[2] = new Employees("EE003", "Vinh", 19, true);
//        arr[3] = new Employees("EE004", "Vũ", 17, false);
//        arr[4] = new Employees("EE005", "Sơn", 23, false);
//        dao.delete(arr[0].getIdEmployees());
//        for(int i = 0; i< arr.length; i++){
//            if(dao.insert(arr[i])){
//                System.err.println("Thêm mới 1 nhân viên");
//            }
//        }

//          List<Employees> list = dao.getAll();
//          
//          for(Employees e : list){
//              System.out.println(e.toString());
//          }
//
//        for(int i  = 3; i< arr.length;i ++){
//            dao.update(arr[i]);
//        }
//        System.out.println("Tìm kiếm");
//        list = dao.findModel("EE004");
//        System.out.println(list.get(0).toString());

//        UserDAO userDO = new UserDAO();
////        userDO.insert(new Users("Vinh", "123", "EE003"));
//        
//        List<Users> list = userDO.getAll();
//        for(Users u : list){
//            System.out.println(u.toString());
//        }
//            CategoryDAO cate = new CategoryDAO();
//            cate.insert(new Category("Đồ ăn"));
//            List<Category> list = cate.getAll();
//            System.out.println(list.get(0).toString());

//            System.out.println(cate.findModel(1).get(0).toString());
        ItemDAO item = new ItemDAO();
        item.update(new Item(1,"Ếch xào xả", 800000, 1));
        List<Item> list = item.getAll();
        for(Item i : list){
            System.out.println(i.toString());
        }
    }
    
}
