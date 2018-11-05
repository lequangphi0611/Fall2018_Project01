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
public class ItemOrder extends Item {

    private int quantity;

    public ItemOrder() {
    }

    public ItemOrder(int idItem, String itemName, long price, int idCategory, int quantity) {
        super(idItem, itemName, price, idCategory);
        this.quantity = quantity;
    }

    public ItemOrder(Item item, int quantity) {
        super(item.getIdItem(), item.getItemName(), item.getPrice(), item.getIdCategory());
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long sumPrice() {
        return super.getPrice() * this.quantity;
    }

    @Override
    public String toString() {
        return super.toString()+":"+this.quantity ;
    }
}
