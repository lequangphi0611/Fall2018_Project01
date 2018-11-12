/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDao;
import Model.Category;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class CategoryDAO extends DAO<Category> implements IDao<Category, Integer> {

    @Override
    public Category getModel() throws SQLException {
        return new Category(rs.getInt("IdCategory"), rs.getString("CategoryName"));
    }

    @Override
    public List<Category> getAll() {
        return executeQuery("select * from Category");
    }

    @Override
    public boolean insert(Category model) {
        return executeUpdate("insert into Category (CategoryName) values (?)",
                model.getCategoryName()
        );
    }

    @Override
    public boolean update(Category model) {
        return executeUpdate("update Category set CategoryName = ? where IdCategory = ?s",
                model.getCategoryName(),
                model.getIdCategory()
        );
    }

    @Override
    public boolean delete(Integer object) {
        return executeUpdate("delete from Category where IdCategory = ?", object);
    }

    @Override
    public List<Category> findModel(Integer object) {
        return executeQuery("select * from Category where IdCategory = ?", object);
    }

}
