/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Quang Phi
 */
public class Bill{

    private String idBill;
    private String idEmployees;
    private Date datrPayment;
    private int tableNumber;
    private long sumPrice;
    private long sale;

    public Bill() {
    }

    public Bill(String idBill, String idEmployees, Date datrPayment, int tableNumber, long sumPrice, long sale) {
        this.idBill = idBill;
        this.idEmployees = idEmployees;
        this.datrPayment = datrPayment;
        this.tableNumber = tableNumber;
        this.sumPrice = sumPrice;
        this.sale = sale;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(String idEmployees) {
        this.idEmployees = idEmployees;
    }

    public Date getDatrPayment() {
        return datrPayment;
    }

    public void setDatrPayment(Date datrPayment) {
        this.datrPayment = datrPayment;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(long sumPrice) {
        this.sumPrice = sumPrice;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    public void setSaleToPercent(int percent) {
        this.sale = (this.sumPrice * percent) / 100;
    }
    
    public long getTotal(){
        return this.sumPrice - this.sale;
    }

}
