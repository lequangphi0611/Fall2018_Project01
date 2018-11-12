/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Data.UserData;
import Model.Users;
import java.util.Date;

/**
 *
 * @author Quang Phi
 */
public class new_Main {

    public static void main(String[] args) {
      diff();
    }

    static void diff() {
        String hd1 = MyLibry.getRandomIdBill(10);
        String hd2 = MyLibry.getRandomIdBill(10);
        String hd3 = MyLibry.getRandomIdBill(10);
        String hd4 = MyLibry.getRandomIdBill(10);
        String hd5 = MyLibry.getRandomIdBill(10);
        System.out.println(hd1);
        System.out.println(hd2);
        System.out.println(hd3);
        System.out.println(hd4);
        System.out.println(hd5);
        hd1 = MyLibry.getRandomIdBill(20);
        hd2 = MyLibry.getRandomIdBill(20);
        hd3 = MyLibry.getRandomIdBill(20);
        hd4 = MyLibry.getRandomIdBill(20);
        hd5 = MyLibry.getRandomIdBill(20);
        System.out.println(hd1);
        System.out.println(hd2);
        System.out.println(hd3);
        System.out.println(hd4);
        System.out.println(hd5);
        hd1 = MyLibry.getRandomIdBill(20);
        hd2 = MyLibry.getRandomIdBill(20);
        hd3 = MyLibry.getRandomIdBill(20);
        hd4 = MyLibry.getRandomIdBill(20);
        hd5 = MyLibry.getRandomIdBill(20);
        System.out.println(hd1);
        System.out.println(hd2);
        System.out.println(hd3);
        System.out.println(hd4);
        System.out.println(hd5);
        hd1 = MyLibry.getRandomIdBill(20);
        hd2 = MyLibry.getRandomIdBill(20);
        hd3 = MyLibry.getRandomIdBill(20);
        hd4 = MyLibry.getRandomIdBill(20);
        hd5 = MyLibry.getRandomIdBill(20);
        System.out.println(hd1);
        System.out.println(hd2);
        System.out.println(hd3);
        System.out.println(hd4);
        System.out.println(hd5);
    }

    static void testLogin() {
        try {
            if (UserData.login("Phi", "123")) {
                System.out.println("Đăng nhập thành công");
            }
            System.out.println("Thông tin đăng nhập : " + UserData.getUserInfor().getIdEmployees());
        } catch (Error ex) {
            System.out.println(ex.getMessage());
        }
    }
}
