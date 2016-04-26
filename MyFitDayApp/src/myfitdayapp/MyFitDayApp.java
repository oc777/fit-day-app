/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olgachristensen
 */
public class MyFitDayApp {

    private static DBHandler dbh;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws SQLException {
        //DriverManager.getConnection("jdbc:derby:;shutdown=true");
        
        //dbh = new DBHandler();
        //createTables();
        
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        
    }
    
    private static void createTables() {
        try {
            DatabaseMetaData dmd = dbh.connection.getMetaData();
            
            ResultSet rsFood = dmd.getTables(null, null, "food", null);
            if (rsFood.next()) {
            } 
            else
                dbh.createTable();
            
          
            
        } catch (SQLException ex) {
            Logger.getLogger(MyFitDayApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
