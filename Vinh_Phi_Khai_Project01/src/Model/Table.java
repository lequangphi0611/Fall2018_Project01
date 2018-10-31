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

    private int tableName;
    List<Item> item;
    private boolean status = true;//true là bàng trống...false là bàn đg có khách

    public Table() {
    }

    public Table(int tableName, List<Item> item, boolean status) {
        this.tableName = tableName;
        this.item = item;
        this.status = status;
    }

    public int getTableName() {
        return tableName;
    }

    public String getTableNameString() {
        return "Bàn số " + tableName;
    }

    public void setTableName(int tableName) {
        this.tableName = tableName;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
