package myfitdayapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olgachristensen
 */
public class DBHandler {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:appdb;create=true";
    public Connection connection;
    
    public DBHandler() {
        try {
            //if (connection != null) 
                //connection.close();
            
            connection = DriverManager.getConnection(URL);
            Class.forName(DRIVER).newInstance();
            
            if (connection != null)
                System.out.println("connected");
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable() {
        try {
            connection.createStatement().execute("create table food (date date, name varchar(20), cal int, fat int, carbs int, protein int)");
            connection.createStatement().execute("create table sport (date date, name varchar(20), cal int)");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertData(String date, String name, int cal, int f, int c, int p) {
        try {
            connection.createStatement().execute("insert into food values('"
                    +date+"','"+name+"',"+cal+","+f+","+c+","+p+")");
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printAll() {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from food");
            while(rs.next())
                System.out.println("Date: " + rs.getString("date") + "Name: " + rs.getString("name") + ", cal: " 
                        + rs.getString("cal") + ", fat: " + rs.getString("fat") + ", carbs: "
                        + rs.getString("carbs") + ", protein: " + rs.getString("protein"));
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void printTodayFood(String date) {
        //SELECT * FROM Customers where Country='Mexico';
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from food where date=" + date);
            while(rs.next())
                System.out.println("Name: " + rs.getString("name") + ", cal: " 
                        + rs.getString("cal"));
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getTodayFood(String date) throws SQLException {
        
        String str = "";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from food where date='" + date + "'");
        
        
        while(rs.next()) {
            
            System.out.println("");
            System.out.format("%20s", rs.getString(2) + " | ");
            System.out.format("%5s", rs.getString(3) + " | ");
            
            str += rs.getString(2);
            str += ".....";
            str += rs.getString(3);
            str += "\n";
            
            
        }
        
        if (connection != null)
            connection.close();
        
        return str;
    }
    
}
