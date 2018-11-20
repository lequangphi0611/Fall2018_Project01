/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.ItemDAO;

/**
 *
 * @author Quang Phi
 */
public class Item {

    private int idItem;
    private String itemName;
    private String unit;
    private long price = 0;
    private String idCategory;
    private boolean isSell = true;

    public Item() {
    }

    public Item(int idItem, String itemName, String unit, long price, String idCategory,boolean isSell) {
        this.idItem = idItem;
        this.itemName = itemName;
        this.unit = unit;
        this.price = price;
        this.idCategory = idCategory;
        this.isSell = isSell;
    }

    public Item(Item item) {
        this.idItem = item.getIdItem();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.unit = item.getUnit();
        this.idCategory = item.getIdCategory();
        this.isSell = item.isSell();
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

    public boolean isSell() {
        return isSell;
    }

    public void setIsSell(boolean isSell) {
        this.isSell = isSell;
    }
    
    public int getQuantityRemain(){
        return new ItemDAO().itemInWare(idItem);
    }

    @Override
    public String toString() {
        return this.itemName;
    }
    
    
    
    
}
