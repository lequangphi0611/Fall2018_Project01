/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class Table {

    private final int TABLENUM;
    private List<ItemOrder> itemOrder;
    private boolean status = true;//true là bàng trống...false là bàn đg có khách

    public Table(int tableNum) {
        this.TABLENUM = tableNum;
        setStatus();
    }

    public Table(int tableNum, List<ItemOrder> itemOrder) {
        this.TABLENUM = tableNum;
        this.itemOrder = itemOrder;
        setStatus();
    }   
    
    public List<ItemOrder> getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(List<ItemOrder> itemOrder) {
        this.itemOrder = itemOrder;
        setStatus();
    }

    public int getTableNum() {
        return TABLENUM;
    }

    public String getTableName() {
        return "Bàn số " + TABLENUM;
    }

    public boolean isStatus() {
        return status;
    }
    
    public String statusToString(){
        return this.status ? "Bàn đang trống" : "Đang có khách";
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
    
    private void setStatus(){
        this.status = this.itemOrder == null || this.itemOrder.isEmpty();
    }

    public long sumPrice(){
        long result = 0;
        for(int i = 0; i < itemOrder.size(); i++){
            result += itemOrder.get(i).sumPrice();
        }
        return result;
    }
    
}
