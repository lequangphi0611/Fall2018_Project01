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
    private boolean idRole = false;

    public Employees() {
    }

    public Employees(String idEmployees, String name, int age, boolean idRole) {
        this.idEmployees = idEmployees;
        this.name = name;
        this.age = age;
        this.idRole = idRole;
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

    public boolean isIdRole() {
        return idRole;
    }

    public void setIdRole(boolean idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
