/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Model.Users;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class UserDAO extends DAO<Users> implements IDao<Users, String> {

    @Override
    public Users getModel() throws SQLException {
        return new Users(
                rs.getString("UserName"),
                rs.getString("Pass"),
                rs.getString("IdEmployees")
        );
    }

    @Override
    public List<Users> getAll() {
        return executeQuery("select * from Users");
    }

    @Override
    public boolean insert(Users model) {
        return executeUpdate(
                "insert into Users(UserName,Pass,IdEmployees) values (?,?,?)",
                model.getUserName(),
                model.getPassword(),
                model.getIdEmployees()
        );
    }

    @Override
    public boolean update(Users model) {
        return executeUpdate(
                "update Users set Pass = ? where UserName = ?",
                model.getPassword(),
                model.getUserName()
        );
    }

    @Override
    public boolean delete(String object) {
        return executeUpdate("delete from Users where UserName = ?", object);
    }

    @Override
    public List<Users> findModel(String object) {
        return executeQuery("select * from Users where UserName = ?", object);
    }

}
