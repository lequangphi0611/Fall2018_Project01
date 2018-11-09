/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Users;

/**
 *
 * @author Quang Phi
 */
public class UserData {

    private static Users user = null;

    public static void login(Users user) {
        UserData.user = user;
    }

    public static void logOut() {
        UserData.user = null;
    }

    public static boolean isLogin() {
        return UserData.user != null;
    }

    public static Users getUserInfor() {
        return UserData.user;
    }

}
