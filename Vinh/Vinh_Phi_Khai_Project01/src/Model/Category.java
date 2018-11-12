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
public class Category {
    private int idCategory;
    private String categoryName;

    public Category() {
    }

    public Category(int idCategory,String categoryName) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
    }
    
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return this.categoryName; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
