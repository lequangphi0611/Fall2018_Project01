/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Library.Convert;
import java.util.Date;

/**
 *
 * @author Quang Phi
 */
public class ImportItem {
    
    private String idImport;
    private int idItem;
    private String idEmployees;
    private Date timeImport;
    private Date dateImport;
    private int quantityReceived;

    public ImportItem() {
    }

    public ImportItem(String idImport, int idItem, String idEmployees, Date timeImport, Date dateImport, int quantityReceived) {
        this.idImport = idImport;
        this.idItem = idItem;
        this.idEmployees = idEmployees;
        this.timeImport = timeImport;
        this.dateImport = dateImport;
        this.quantityReceived = quantityReceived;
    }

    public String getIdImport() {
        return idImport;
    }

    public void setIdImport(String idImport) {
        this.idImport = idImport;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(String idEmployees) {
        this.idEmployees = idEmployees;
    }

    public Date getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Date timeImport) {
        this.timeImport = timeImport;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }
    
    public String getFullTime(){
        String result = Convert.formatDate(timeImport, "HH:mm:ss ");
        result += Convert.formatDate(dateImport, "dd/MM/yyyy");
        return result;
    }

    public int getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(int quantityReceived) {
        this.quantityReceived = quantityReceived;
    }
    
}
