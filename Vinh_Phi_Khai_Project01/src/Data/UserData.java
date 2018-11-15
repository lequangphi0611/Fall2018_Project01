/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DAO.UserDAO;
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

    public static boolean login(String user, String password) throws Error {
        try {
            Users users = new UserDAO().findModel(user).get(0);

            if (!users.getPassword().equals(password)) {
                throw new Error("Sai mật khẩu ! Vui lòng nhập lại !");
            }

            UserData.user = users;
            return true;

        } catch (IndexOutOfBoundsException ex) {
            throw new Error("Không tìm thấy thông tin đăng nhập !");
        }
    }

    public static void changePassword(String oldPassword , String newPassword) throws Error{
        if(!oldPassword.equals(user.getPassword())){
            throw new Error("Mật khẩu cũ không chính xác !");
        }
        UserData.user.setPassword(newPassword);
        new UserDAO().update(UserData.user);
    }
    
}
