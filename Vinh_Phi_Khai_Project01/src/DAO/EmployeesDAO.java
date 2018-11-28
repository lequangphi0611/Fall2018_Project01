/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Model.Employees;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class EmployeesDAO extends DAO<Employees> implements IDao<Employees, String> {

    @Override
    public Employees getModel() throws SQLException {
        return new Employees(
                rs.getString("IdEmployees"),
                rs.getString("Name"),
                rs.getInt("Age"),
                rs.getInt("Sex") != 0,
                rs.getString("PhoneNumber"),
                rs.getInt("Role") != 0,
                rs.getString("Address")
        );
    }

    @Override
    public List<Employees> getAll() {
        return executeQuery("select * from Employees");
    }

    @Override
    public boolean insert(Employees model) {
        return executeUpdate(
                "insert into Employees"
                + "(IdEmployees,Name,Age,Sex,PhoneNumber,Role,Address)"
                + " values (?,?,?,?,?,?,?)",
                model.getIdEmployees(),
                model.getName(),
                model.getAge(),
                model.isSex() ? 1 : 0,
                model.getPhoneNumber(),
                model.isRole() ? 1 : 0,
                model.getAddress()
        );
    }

    @Override
    public boolean update(Employees model) {
        return executeUpdate(
                "update Employees"
                + " set Name = ?,Age = ?,Sex = ?,PhoneNumber=?,Role = ?,"
                + "Address = ? where IdEmployees = ?",
                model.getName(),
                model.getAge(),
                model.isSex() ? 1 : 0,
                model.getPhoneNumber(),
                model.isRole() ? 1 : 0,
                model.getAddress(),
                model.getIdEmployees()
        );
    }

    @Override
    public boolean delete(String object) {
        return executeUpdate("delete from Employees where IdEmployees = ?", object);
    }

    @Override
    public List<Employees> findModel(String object) {
        return executeQuery("select * from Employees where IdEmployees = ?", object);
    }

    public List<Employees> isNotUser() {
        return executeQuery("select * from Employees where IdEmployees not in(select IdEmployees from Users)");
    }

}
