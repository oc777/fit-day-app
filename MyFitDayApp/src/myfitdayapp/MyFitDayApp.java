
package myfitdayapp;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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
        
        //dbh = new DBHandler();
        //deleteTodayData();
        
        MainFrame mf = new MainFrame();
        
        // opens frame in top-right corner
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - mf.getWidth();
        int y = 0;
        mf.setLocation(x, y);
        
        
        mf.setVisible(true);
        
    }
    
    
    
    // help method for testing purposes
    // removes data from DB for today
    // DELETE from end product
    private static void deleteTodayData() {
        try {
            dbh.connection.createStatement().execute("delete from food where date='" + dbh.getDate() + "'");
            dbh.connection.createStatement().execute("delete from totals where date='" + dbh.getDate() + "'");
            dbh.connection.createStatement().execute("delete from sport where date='" + dbh.getDate() + "'");

            System.out.println("data deleted");
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
