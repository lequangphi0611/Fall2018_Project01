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

    public ItemOrder(int idItem, String itemName, String unit, long price, String idCategory, int quantity) {
        super(idItem, itemName, unit, price, idCategory);
        this.quantity = quantity;
    }

    public ItemOrder(Item item, int quantity) {
        super(item.getIdItem(), item.getItemName(), item.getUnit(), item.getPrice(), item.getIdCategory());
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

    //Hợp nhất 2 mặt hàng giống nhau (Hợp nhất số lượng của 2 mặt hàng)
    //Dùng cho khi gọi thêm món...
    public static ItemOrder merge(ItemOrder itemOrder1, ItemOrder itemOrder2) {
        if (itemOrder1.equals(itemOrder2)) {
            int newQuantity = itemOrder1.getQuantity() + itemOrder2.getQuantity();
            itemOrder1.setQuantity(newQuantity);
        }
        return itemOrder1;
    }

    //Sữ dụng khi người dùng trả lại  hàng
    public static ItemOrder giveBackItem(ItemOrder item, int giveBackNum) {
        if (item.getQuantity() < giveBackNum || giveBackNum < 0) {
            throw new Error(
                    "Lỗi ! giveBackNum phải bé hơn hoặc bằng số lượng của item "
                    + "và phải lớn hơn 0"
            );
        }
        int newQuantity = item.getQuantity() - giveBackNum;
        item.setQuantity(newQuantity);
        return item;
    }

    //So sánh 2 mặt hàng(so sánh tên và giá)
    public boolean equals(ItemOrder item) {
        return this.getItemName().equals(item.getItemName())
                && this.getPrice() == item.getPrice();
    }

}
