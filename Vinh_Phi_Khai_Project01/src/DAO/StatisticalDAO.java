/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Library.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static Library.ConnectionDB.*;
import Library.Convert;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class StatisticalDAO {

    ResultSet rs = null;
    
    public static final String[] FILTER = {
        "Bán chạy nhất",
        "Bán ít nhất",
        "Hàng tồn kho",
        "Hàng sắp hết",
        "Nhập nhiều nhất",
        "Doanh thu cao nhất"
    };
    
    private String getStoreProduceForRevenue(String typeStatistics){
        String sql = "exec ";
        if(typeStatistics.equalsIgnoreCase("Ngày")){
            sql += "sp_ThongKeDoanhThuTheoNgay";
        }else if(typeStatistics.equalsIgnoreCase("Tháng")){
            sql += "sp_ThongKeDoanhThuTheoThang";
        }else if(typeStatistics.equalsIgnoreCase("Năm")){
            sql += "sp_ThongKeDoanhThuTheoNam";
        }else{
            throw new Error("Lỗi ! "+typeStatistics+" Không phải là Ngày || Tháng || Năm");
        }
        return sql;
    }
    
    private Object[] getObjectForRevenue(String typeStatistics) throws SQLException{
        Object[] ob = new Object[6];
        if(typeStatistics.equalsIgnoreCase("Tháng")){
            ob[0] ="Tháng " + rs.getInt("Thang")+" , "+rs.getInt("Nam");
        }else if(typeStatistics.equalsIgnoreCase("Ngày")){
            ob[0] = Convert.formatDate(rs.getDate("times"), "dd-MM-yyyy");
        }else{
            ob[0] = rs.getInt("times");
        }
        ob[1] = rs.getInt("SoLuongGiaoDich");
        ob[2] = Convert.toMoney(rs.getLong("DoanhThuThapNhat"));
        ob[3] = Convert.toMoney(rs.getLong("DoanhThuCaoNhat"));
        ob[4] = Convert.toMoney(rs.getLong("DoanhThuTrungBinhMoiGiaoDich"));
        ob[5] = Convert.toMoney(rs.getLong("TongDoanhThu"));
        return ob;
    }
    
    public List<Object[]> getRevenueStatistics (String typeStatistics){
        String sql = getStoreProduceForRevenue(typeStatistics);
        List<Object[]> list = new ArrayList<>();
        try {
            rs = resultQuery(sql);
            while(rs.next()){
                list.add(getObjectForRevenue(typeStatistics));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                closeConnect(rs, (PreparedStatement) rs.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    private int getQuantityReceivedOf(int idItem){
        String sql = "Select sum(QuantityReceived) from Import where Iditem = ? group by Iditem ";
        ResultSet rss = null;
        try {
            rss = ConnectionDB.resultQuery(sql, idItem);
            return rss.next() ? rss.getInt(1) : 0;
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }finally{
            try {
                ConnectionDB.closeConnect(rss, (PreparedStatement) rss.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Object[] getQuantityIsSellAndSumPrice(int idItem){
        String sql = "select sum(Quantity),sum(Quantity * Price) from BillDetail where IdItem = ? group by IdItem";
        ResultSet resultSet = null;
         try {
            resultSet = ConnectionDB.resultQuery(sql, idItem);
            return resultSet.next() ? new Object[]{resultSet.getInt(1),resultSet.getLong(2)}
                                    : new Object[]{0,0}; 
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally{
            try {
                ConnectionDB.closeConnect(resultSet, (PreparedStatement) resultSet.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Object[] getObjectforItem() throws SQLException{
        int idItem = rs.getInt("IdItem");
        Object[] billDTInfor = getQuantityIsSellAndSumPrice(idItem);
        return new Object[]{
            rs.getString("ItemName"),
            getQuantityReceivedOf(idItem),
            billDTInfor[0],
            new WareDAO().selectByItem(idItem).getQuantityRemain(),
            Convert.toMoney((Number) billDTInfor[1])
        };
    }
    
    private String getSQL(String filter){
        String result = "Select it.IdItem, it.ItemName,sum(bd.Quantity * bd.Price) SumPrice"
                + " from Item it inner join BillDetail bd on it.IdItem = bd.IdItem "
                + "group by it.IdItem, it.ItemName order by SumPrice desc";
        if(filter.equals(FILTER[0])){//Bán chạy nhất
            result = "select it.IdItem, it.ItemName, sum(bd.Quantity) from Item it "
                    + "inner join BillDetail bd on it.IdItem = bd.IdItem group by "
                    + "it.IdItem, it.ItemName order by sum(Quantity) desc";
        }else if(filter.equals(FILTER[1])){
            result = "select it.IdItem, it.ItemName, sum(bd.Quantity) from Item it "
                    + "inner join BillDetail bd on it.IdItem = bd.IdItem group by "
                    + "it.IdItem, it.ItemName order by sum(Quantity) asc";
        }else if(filter.equals(FILTER[2])){
            result = "select * from Item inner join Ware on Item.IdItem = Ware.IdItem "
                    + "order by Ware.QuantityRemain desc";
        }else if(filter.equals(FILTER[3])){
            result = "select * from Item inner join Ware on Item.IdItem = Ware.IdItem "
                    + "order by Ware.QuantityRemain asc";
        }else if(filter.equals(FILTER[4])){
            result = "Select it.IdItem, it.ItemName, sum(ip.QuantityReceived) from Item it "
                    + "inner join Import ip on it.IdItem = ip.IdItem "
                    + "group by it.IdItem, it.ItemName "
                    + "order by sum(ip.QuantityReceived) desc";
        }
        return result;
    }
    
    public List<Object[]> getListForItemStatistical(String filter){
        List<Object[]> list = new ArrayList<>();
        String sql = getSQL(filter);
        try {
            rs = resultQuery(sql);
            while(rs.next()){
                list.add(getObjectforItem());
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                closeConnect(rs, (PreparedStatement) rs.getStatement());
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
}
