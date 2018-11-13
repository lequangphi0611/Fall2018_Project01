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
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Item item = new Item(1, "Thịt ba rọi", "Kg", 100, "4445");
        Item item2 = new Item(1, "Thịt ba rọi", "Kg", 100, "4445");

        ItemOrder itemOrder1 = new ItemOrder(item, 1);
        ItemOrder itemOrder2 = new ItemOrder(item2, 2);
        Table tb = new Table(1);
        tb.push(itemOrder1);
        for (ItemOrder i : tb.getItemOrder()) {
            System.out.println(i.getItemName() + "--" + i.getQuantity());
        }
        System.out.println("----------Sau khi trừ----------");
        tb.giveBackItem(itemOrder1, 1);
        System.out.println("STT " + itemOrder1.getQuantity());
        if (tb.isEmpty()) {
            System.out.println("Trống hàng");
        }
        for (ItemOrder i : tb.getItemOrder()) {
            System.out.println(i.getItemName() + "--" + i.getQuantity());
        }
        for (int i = 0; i < 30; i++) {
            tb.push(itemOrder2);
            System.out.println(i + " : " + itemOrder1.getQuantity());
        }
        if (tb.isEmpty()) {
            System.out.println("Trống hàng");
        }
        for (ItemOrder i : tb.getItemOrder()) {
            System.out.println(i.getItemName() + "--" + i.getQuantity());
        }
    }

    static void Table() {
        Item item = new Item(1, "Thịt ba rọi", "Kg", 100, "4445");
        Item item2 = new Item(1, "Thịt ba rọi", "Kg", 100, "4445");
        ItemOrder itemOrder1 = new ItemOrder(item, 5);
        ItemOrder itemOrder2 = new ItemOrder(item2, 2);
        Table tb01 = new Table(1);
        tb01.push(itemOrder2);
        System.out.println(tb01.getItemOrder().get(0).getQuantity());
        tb01.push(itemOrder1);
        System.out.println(tb01.getItemOrder().get(0).getQuantity());
        item2 = new Item(1, "Thịt Mỡ", "Kg", 50000, "4445");
        itemOrder2 = new ItemOrder(item2, 3);
        tb01.push(itemOrder2);
        for (ItemOrder its : tb01.getItemOrder()) {
            System.out.println(its.getItemName() + " - " + its.getQuantity());
        }
        System.out.println("----------------");
        item2 = new Item(1, "Thịt Mỡ Công nghiệp", "Kg", 50000, "4445");
        itemOrder2 = new ItemOrder(item2, 7);
        tb01.push(itemOrder1);
        tb01.push(itemOrder1);
        tb01.push(itemOrder2);
        for (ItemOrder its : tb01.getItemOrder()) {
            System.out.println(its.getItemName() + " - " + its.getQuantity() + " - Giá : " + its.sumPrice() + " - Giá gốc : " + its.getPrice());
        }
        System.out.println("Tổng giá : " + tb01.sumPrice());
    }

}
