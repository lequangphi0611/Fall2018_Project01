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
public class Item {
    private int idItem;
    private String itemName;
    private long price;
    private String idCategory;

    public Item() {
    }

    public Item(int idItem, String itemName, long price, String idCategory) {
        this.idItem = idItem;
        this.itemName = itemName;
        this.price = price;
        this.idCategory = idCategory;
    }
    public Item(String itemName, long price, String idCategory) {
        this.itemName = itemName;
        this.price = price;
        this.idCategory = idCategory;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return this.itemName+":"+this.price; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
