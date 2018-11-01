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
public class Bill {
    private String IdBill;
    private String IdContentBill;
    private String idEmployees;
    private Date datePayment;
    private long sumPrice;

    public Bill() {
    }

    public Bill(String IdBill, String IdContentBill, String idEmployees, Date datePayment, long sumPrice) {
        this.IdBill = IdBill;
        this.IdContentBill = IdContentBill;
        this.idEmployees = idEmployees;
        this.datePayment = datePayment;
        this.sumPrice = sumPrice;
    }

    public String getIdBill() {
        return IdBill;
    }

    public void setIdBill(String IdBill) {
        this.IdBill = IdBill;
    }

    public String getIdContentBill() {
        return IdContentBill;
    }

    public void setIdContentBill(String IdContentBill) {
        this.IdContentBill = IdContentBill;
    }

    public String getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(String idEmployees) {
        this.idEmployees = idEmployees;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(long sumPrice) {
        this.sumPrice = sumPrice;
    }
    
}
