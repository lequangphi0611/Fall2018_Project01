/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phi
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String t = "Phi,Khải,Vinh,Son,VSVS";
        String[] tAfter = myLibrary.stringToArray(t, ',');
        for(String t1 : tAfter){
            System.out.println(t1);
        }
    }
    
    static void testConnect(){
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnect();
            if(conn != null){
                System.out.println("Connected");
            }else{
                System.out.println("Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDB.closeConnect(null, null, conn);
        }
    }
    
   
    
}
