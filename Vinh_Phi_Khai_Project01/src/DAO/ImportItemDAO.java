/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Library.ConnectionDB;
import Library.MyLibry;
import Model.ImportItem;
import Model.Ware;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class ImportItemDAO extends DAO<ImportItem> {
    
    WareDAO wareDAO = new WareDAO();

    @Override
    public ImportItem getModel() throws SQLException {
        return new ImportItem(
                rs.getInt("IdImport"),
                rs.getInt("IdItem"),
                rs.getString("IdEmployees"),
                rs.getTime("TimeImport"),
                rs.getDate("DateImport"),
                rs.getInt("QuantityReceived")
        );
    }

    public List<ImportItem> getListForItem(Integer idItem) {
        if(idItem == null){
            return executeQuery("select * from Import order by DateImport desc,TimeImport desc");
        }
        return executeQuery("select * from Import where Iditem = ? order by DateImport desc,TimeImport desc", idItem);
    }
    
    public boolean checkUnduticate(String id){
        return executeQuery("select * from Import where IdImport=?",id).isEmpty();
    } 

    public boolean insert(ImportItem importInfor) {
        return executeUpdate(
                "insert into Import"
                + "(IdItem,IdEmployees,TimeImport,DateImport,QuantityReceived)"
                + " values (?,?,?,?,?)",
                importInfor.getIdItem(),
                importInfor.getIdEmployees(),
                new java.sql.Time(importInfor.getTimeImport().getTime()),
                new java.sql.Date(importInfor.getDateImport().getTime()),
                importInfor.getQuantityReceived()
        ) && wareDAO.addQuantityRemain(new Ware(importInfor.getIdItem(),importInfor.getQuantityReceived()));
    }
    
    public List<Date> getAllDate(){
        List<Date> list = new ArrayList<>();
        try {
            rs = ConnectionDB.resultQuery("select DateImport from Import group by DateImport order by DateImport desc");
            while(rs.next()){
                list.add(rs.getDate(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ConnectionDB.closeConnect(rs, (PreparedStatement) rs.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(ImportItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<ImportItem> selectByDate(Date date){
        return executeQuery("select * from Import where Import.DateImport=? order by TimeImport desc",new java.sql.Date(date.getTime()));
    }

}
