/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
    
    private Object[] getObjectforItem() throws SQLException{
        return new Object[]{
            rs.getString("TenMatHang"),
            rs.getString("TongSoLuongBanDuoc"),
            Convert.toMoney(rs.getLong("BanDuocItNhat")),
            Convert.toMoney(rs.getLong("BanDuocNhieuNhat")),
            Convert.toMoney(rs.getLong("TongTien"))
        };
    }
    
    public List<Object[]> getListForItemStatistical(){
        List<Object[]> list = new ArrayList<>();
        try {
            rs = resultQuery("exec sp_ThongKeMatHangBanDuoc");
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
