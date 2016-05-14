
package myfitdayapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olgachristensen
 */
public class DBHandlerTest {
    DBHandler dbh;
    
    
    public DBHandlerTest() {
        dbh = new DBHandler();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        dbh.connection.createStatement().execute("create table testFood (date date, name varchar(20), cal int, fat int, carbs int, protein int)");
            
    }
    
    @After
    public void tearDown() throws SQLException {
        //rs = null;
        dbh.connection.createStatement().execute("drop table testFood");
    }

    //help method
    private void populateTable() throws SQLException {
        
        
        
    }

    @Test
    public void testInsertDataFoodName() throws SQLException {
        String date = dbh.getDate();
        String name = "nameTest";
        int cal = 100;
        int f = 10;
        int c = 20;
        int p = 30;
        dbh.insertDataFood(date, name, cal, f, c, p);

        
        ResultSet rs = dbh.connection.createStatement().executeQuery("select * from testFood where date='" + dbh.getDate() + "'");
        
        String result = "";
        if (rs.next()) {
            result = rs.getString("name");
            System.out.println(result);
        }
        
        assertEquals("nameTest", result);
        
    }

    
    /*
    @Test
    public void testInsertDataSport() {
        System.out.println("insertDataSport");
        String date = "";
        String name = "";
        int cal = 0;
        DBHandler instance = new DBHandler();
        instance.insertDataSport(date, name, cal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertDataGoal() {
        System.out.println("insertDataGoal");
        String date = "";
        int goal = 0;
        DBHandler instance = new DBHandler();
        instance.insertDataGoal(date, goal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGoal() {
        System.out.println("getGoal");
        String date = "";
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getGoal(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckTotals() {
        System.out.println("checkTotals");
        String date = "";
        DBHandler instance = new DBHandler();
        boolean expResult = false;
        boolean result = instance.checkTotals(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckFood() {
        System.out.println("checkFood");
        String date = "";
        DBHandler instance = new DBHandler();
        boolean expResult = false;
        boolean result = instance.checkFood(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckSport() {
        System.out.println("checkSport");
        String date = "";
        DBHandler instance = new DBHandler();
        boolean expResult = false;
        boolean result = instance.checkSport(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testLatestGoal() {
        System.out.println("latestGoal");
        DBHandler instance = new DBHandler();
        int expResult = 0;
        int result = instance.latestGoal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateTotalsFood() {
        System.out.println("updateTotalsFood");
        String date = "";
        int cal = 0;
        int f = 0;
        int c = 0;
        int p = 0;
        DBHandler instance = new DBHandler();
        instance.updateTotalsFood(date, cal, f, c, p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateTotalsSport() {
        System.out.println("updateTotalsSport");
        String date = "";
        int cal = 0;
        DBHandler instance = new DBHandler();
        instance.updateTotalsSport(date, cal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMacros() {
        System.out.println("getMacros");
        String date = "";
        DBHandler instance = new DBHandler();
        String[] expResult = null;
        String[] result = instance.getMacros(date);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetFoodTotal() {
        System.out.println("getFoodTotal");
        String date = "";
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getFoodTotal(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSportTotal() {
        System.out.println("getSportTotal");
        String date = "";
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getSportTotal(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintAllFood() {
        System.out.println("printAllFood");
        DBHandler instance = new DBHandler();
        instance.printAllFood();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintAllSport() {
        System.out.println("printAllSport");
        DBHandler instance = new DBHandler();
        instance.printAllSport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintAllTotals() {
        System.out.println("printAllTotals");
        DBHandler instance = new DBHandler();
        instance.printAllTotals();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetTodayFood() {
        System.out.println("getTodayFood");
        String date = "";
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getTodayFood(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetTodaySport() {
        System.out.println("getTodaySport");
        String date = "";
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getTodaySport(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDate() {
        System.out.println("getDate");
        DBHandler instance = new DBHandler();
        String expResult = "";
        String result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

*/
    
}
