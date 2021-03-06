/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DAO.EmployeesDAO;
import DAO.UserDAO;
import Model.Employees;
import Model.Users;

/**
 *
 * @author Quang Phi
 */
public class UserData {

    private static Users user = null;

    public static void logOut() {
        UserData.user = null;
    }

    public static boolean isLogin() {
        return UserData.user != null;
    }

    public static Users getUserInfor() {
        return UserData.user;
    }

    public static boolean login(String username, String password) throws Error {
        try {
            Users users = new UserDAO().findModel(username).get(0);

            if (!users.getPassword().equals(password)) {
                throw new Error("Sai mật khẩu ! Vui lòng nhập lại !");
            }

            UserData.user = users;
            return true;

        } catch (IndexOutOfBoundsException ex) {
            throw new Error("Không tìm thấy thông tin đăng nhập !");
        }
    }

    public static void changePassword(String newPassword) {
        UserData.user.setPassword(newPassword);
        new UserDAO().update(UserData.user);
    }
    
    public static Employees getEmployees(){
        return new EmployeesDAO().findModel(user.getIdEmployees()).get(0);
    }

    public static boolean isAdmin() {
        return getEmployees().isRole();
    }

}
