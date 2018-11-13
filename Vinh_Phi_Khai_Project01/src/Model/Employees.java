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
public class Employees {

    private String idEmployees;
    private String name;
    private int age;
    private boolean sex = true;
    private String phoneNumber;
    private boolean role = false;
    private String address;

    public Employees() {
    }

    public Employees(String idEmployees, String name, int age, boolean sex, String phoneNumber, boolean role, String address) {
        this.idEmployees = idEmployees;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.address = address;
    }

    public String getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(String idEmployees) {
        this.idEmployees = idEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean idRole) {
        this.role = idRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
