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
    private String unit;
    private long price;
    private String idCategory;

    public Item() {
    }

    public Item(int idItem, String itemName, String unit, long price, String idCategory) {
        this.idItem = idItem;
        this.itemName = itemName;
        this.unit = unit;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Item(Item item) {
        this.idItem = item.getIdItem();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.unit = item.getUnit();
        this.idCategory = item.getIdCategory();
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        return this.itemName + ":" + this.price; //To change body of generated methods, choose Tools | Templates.
    }

    //So sánh hai Item vs nhau
    public int equals(Item item) {
        if (this.price > item.price) {
            return 1;
        } else if (this.price < item.price) {
            return -1;
        }
        return 0;
    }

}
