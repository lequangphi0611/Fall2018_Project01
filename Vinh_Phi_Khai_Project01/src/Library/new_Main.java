/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Data.UserData;
import Model.Users;

/**
 *
 * @author Quang Phi
 */
public class new_Main {

    public static void main(String[] args) {
        Users user = new Users("PhiPhi", "123123", "0987651");
        System.out.println(UserData.isLogin() ? "Đã đăng nhập" : "Chưa đăng nhập");
        UserData.login(user);
        UserData.getUserInfor().setUserName("Phi mới");
        System.out.println(UserData.isLogin() ? "Đã đăng nhập" : "Chưa đăng nhập");
        System.out.println(UserData.getUserInfor().getUserName());
        UserData.logOut();
        System.out.println(UserData.isLogin() ? "Chưa đăng xuất" : "Đăng xuất rồi");
    }
}
