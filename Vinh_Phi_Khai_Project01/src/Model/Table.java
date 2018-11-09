/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quang Phi
 */
public class Table {

    private final int TABLENUM;
    private List<ItemOrder> itemOrder = new ArrayList<>();

    public Table(int tableNum) {
        this.TABLENUM = tableNum;
    }

    public Table(int tableNum, List<ItemOrder> itemOrder) {
        this.TABLENUM = tableNum;
        this.itemOrder = itemOrder;
    }

    public List<ItemOrder> getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(List<ItemOrder> itemOrder) {
        this.itemOrder = itemOrder;
    }

    public int getTableNum() {
        return TABLENUM;
    }

    public String getTableName() {
        return "Bàn số " + TABLENUM;
    }

    public boolean isEmpty() {
        return this.itemOrder.isEmpty();
    }

    public void clear() {
        this.itemOrder = new ArrayList<>();
    }

    public long sumPrice() {
        long result = 0;
        for (int i = 0; i < itemOrder.size(); i++) {
            result += itemOrder.get(i).sumPrice();
        }
        return result;
    }

    //Tìm vị trí của Mặt hàng trong list...không có thì trả về -1
    public int indexOf(ItemOrder item) {
        int index = -1;
        for (int i = 0; i < this.itemOrder.size(); i++) {
            ItemOrder itemOfList = this.itemOrder.get(i);
            if (itemOfList.equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void remove(int index) {
        itemOrder.remove(index);
    }

    public void set(int index, ItemOrder item) {
        itemOrder.set(index, item);
    }

    public void push(ItemOrder item) {
        int index = indexOf(item);
        if (index < 0) {
            this.itemOrder.add(item);
        } else {
            item = ItemOrder.merge(this.itemOrder.get(index), item);
            set(index, item);
        }
    }

    public void giveBackItem(ItemOrder itemOrder, int giveBackNum) {
        int index = indexOf(itemOrder);
        if (index >= 0) {
            itemOrder = ItemOrder.giveBackItem(itemOrder, giveBackNum);
            if (itemOrder.getQuantity() == 0) {
                remove(index);
            } else {
                set(index, itemOrder);
            }
        }
    }

}
