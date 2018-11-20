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
public class Ware{
    
    private int idItem;
    private int quantityRemain;

    public Ware(int idItem, int quantityRemain) {
        this.idItem = idItem;
        this.quantityRemain = quantityRemain;
    }

    public Ware() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getQuantityRemain() {
        return quantityRemain;
    }

    public void setQuantityRemain(int quantityRemain) {
        this.quantityRemain = quantityRemain;
    }
    
    public boolean isOutOfStock(){
        return this.quantityRemain == 0;
    }
    
}
