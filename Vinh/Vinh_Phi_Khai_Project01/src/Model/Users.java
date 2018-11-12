/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Quang Phi
 */
public class Users {
    private String userName;
    private String pass;
    private String IdEmployees;

    public Users() {
    }

    public Users(String userName, String pass, String IdEmployees) {
        this.userName = userName;
        this.pass = pass;
        this.IdEmployees = IdEmployees;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public String getIdEmployees() {
        return IdEmployees;
    }

    public void setIdEmployees(String IdEmployees) {
        this.IdEmployees = IdEmployees;
    }

    @Override
    public String toString() {
        return "UserName : "+userName + " , Pass "+ pass + " , IdEmployee : "+ IdEmployees;
    }
    
    
}
