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
public class BillDetail {

    private String idBill;
    private int idItem;
    private int quantity;
    private long Price;

    public BillDetail(String idBill) {
        this.idBill = idBill;
    }

    public BillDetail(String idBill, int idItem, int quantity, long Price) {
        this.idBill = idBill;
        this.idItem = idItem;
        this.quantity = quantity;
        this.Price = Price;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long Price) {
        this.Price = Price;
    }
    
    public long sumPrice(){
        return quantity * Price;
    }

}
