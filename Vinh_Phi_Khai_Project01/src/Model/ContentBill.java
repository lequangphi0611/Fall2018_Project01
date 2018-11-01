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
public class ContentBill {
    private String idContent;
    private int tableNum;
    private String content;

    public ContentBill() {
    }

    public ContentBill(String idContent, int tableNum, String content) {
        this.idContent = idContent;
        this.tableNum = tableNum;
        this.content = content;
    }

    public String getIdContent() {
        return idContent;
    }

    public void setIdContent(String idContent) {
        this.idContent = idContent;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
