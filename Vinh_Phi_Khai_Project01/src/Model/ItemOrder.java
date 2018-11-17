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

    public ItemOrder(int idItem, String itemName, String unit, long price, String idCategory, int quantity, boolean isSell) {
        super(idItem, itemName, unit, price, idCategory, isSell);
        this.quantity = quantity;
    }

    public ItemOrder(Item item, int quantity) {
        super(item.getIdItem(), item.getItemName(), item.getUnit(), item.getPrice(), item.getIdCategory(), item.isSell());
        this.quantity = quantity;
    }

    public ItemOrder(ItemOrder order) {
        super(order.getIdItem(), order.getItemName(), order.getUnit(), order.getPrice(), order.getIdCategory(),order.isSell());
        this.quantity = order.quantity;
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
            ItemOrder result = new ItemOrder(itemOrder1);
            int newQuantity = result.getQuantity() + itemOrder2.getQuantity();
            result.setQuantity(newQuantity);
            return result;
        } else {
            throw new Error(
                    "Lỗi ! không thể hợp nhất hai mặt hàng khác nhau"
            );
        }
    }

    //Sữ dụng khi người dùng trả lại  hàng
    public static ItemOrder giveBackItem(ItemOrder item, int giveBackNum) {
        if (item.getQuantity() < giveBackNum || giveBackNum < 0) {
            throw new Error(
                    "Lỗi ! giveBackNum phải bé hơn hoặc bằng số lượng của item "
                    + "và phải lớn hơn 0"
            );
        }
        ItemOrder result = new ItemOrder(item);
        int newQuantity = result.getQuantity() - giveBackNum;
        result.setQuantity(newQuantity);
        return result;
    }

    //So sánh 2 mặt hàng(so sánh tên và giá)
    public boolean equals(ItemOrder item) {
        return this.getItemName().equals(item.getItemName())
                && this.getPrice() == item.getPrice();
    }

}
