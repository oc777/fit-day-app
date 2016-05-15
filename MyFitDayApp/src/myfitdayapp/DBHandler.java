package myfitdayapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for handling SQL queries
 * 
 * @author olgachristensen
 */
public class DBHandler {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:appdb;create=true";
    public Connection connection;
    public String[] macros;
    
    public DBHandler() {
        // macros for the day
        macros = new String[3];
        
        
        // establish connection to DB
        try {
            connection = DriverManager.getConnection(URL);
            Class.forName(DRIVER).newInstance();
            
            if (connection != null)
                System.out.println("connected");
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // create number of tables in DB
    public void createTable() {
        try {
            // stores food consumption for each day
            // multiple entries per date allowed
            connection.createStatement().execute("create table food (date date, name varchar(20), cal int, fat int, carbs int, protein int)");
            
            // stores activities for each day
            // multiple entries per date allowed
            connection.createStatement().execute("create table sport (date date, name varchar(20), cal int)");
            
            // stores totals of consumption, activities, macros and goal for each day
            // single entrie per date
            connection.createStatement().execute("create table totals (date date, goal int, food int, sport int, fat int, carbs int, protein int)");
            
            // list of foods
            connection.createStatement().execute("create table foodlist (id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(20), cal int, fat int, carbs int, protein int)");    
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // is invoked when a consumption entry is made
    // stores entry in food table
    // updates totals
    // updates macros
    public void insertDataFood(String date, String name, int cal, int f, int c, int p) {
        try {
            connection.createStatement().execute("insert into food values('"
                    +date+"','"+name+"',"+cal+","+f+","+c+","+p+")");
            
            updateTotalsFood(date, cal, f, c, p);
            
            //printAllTotals();
            macros = getMacros(date);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // is invoked when an activity entry is made
    // stores entry in sport table
    public void insertDataSport(String date, String name, int cal) {
        try {
            connection.createStatement().execute("insert into sport values('"
                    +date+"','"+name+"',"+cal+")");
            
            updateTotalsSport(date, cal);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // inserts goal value in totals table
    public void insertDataGoal(String date, int goal) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'"); 

            // if there is no entry made for today
            if (!rs.next())
                st.execute("insert into totals (date, goal) values('"+date+"',"+goal+")");

            // if the goal for today has been already set
            else
                st.execute("update totals set goal=" + goal +" where date='" + date + "'");
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // returns date specific goal from totals in String form
    public String getGoal(String date) {
        String goal = "";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'"); 

            if (rs.next())
                goal = rs.getString("goal");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return goal;
    }
    
    // checks if any entry for the date was made in totals
    public boolean checkTotals(String date) {
        boolean entries = false;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rs.next())
                entries = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return entries;
    }
    
    // checks if any entry for the date was made in food
    public boolean checkFood(String date) {
        boolean entries = false;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from food where date='" + date + "'");
            
            if (rs.next())
                entries = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return entries;
    }
    
    // checks if any entry for the date was made in sport
    public boolean checkSport(String date) {
        boolean entries = false;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from sport where date='" + date + "'");
            
            if (rs.next())
                entries = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return entries;
    }
    
    // returns last available goal from totals
    public int latestGoal() {
        int goal = 0;
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from totals"); 

            if (rs.last()) {
                goal = rs.getInt("goal");
                

            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return goal;
    }
    
    // updates totals when new food entry is made
    public void updateTotalsFood(String date, int cal, int f, int c, int p) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rs.next()) {
            
                // if it is the first entry for the date
                if (rs.getString("food") == null)
                    //st.execute("insert into totals (date, food, fat, carbs, protein) values('"+date+"',"+cal+","+f+","+c+","+p+")");
                    st.execute("update totals set food="+cal+",fat="+f+",carbs="+c+",protein="+p 
                            +" where date='" + date + "'");

                // update existing entry
                else {
                    int calories = Integer.parseInt(rs.getString("food")) + cal;
                    int fat = Integer.parseInt(rs.getString("fat")) + f;
                    int carbs = Integer.parseInt(rs.getString("carbs")) + c;
                    int protein = Integer.parseInt(rs.getString("protein")) + p;

                    st.execute("update totals set food="+calories+",fat="+fat+",carbs="+carbs+",protein="+protein 
                            +" where date='" + date + "'");

                }
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // updates totals when new sport entry is made
    public void updateTotalsSport(String date, int cal) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rs.next()) {
            
                // if it is the first entry for the date
                if (rs.getString("sport") == null)
                    st.execute("update totals set sport="+cal+" where date='" + date + "'");

                // update existing entry
                else {
                    int calories = Integer.parseInt(rs.getString("sport")) + cal;
                    
                    st.execute("update totals set sport="+calories+" where date='" + date + "'");

                }
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // returns array of Strings with macros data for the date
    public String[] getMacros(String date) {
        String[] res = new String[3];
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rs.next()) {
                res[0] = rs.getString("fat");
                res[1] = rs.getString("carbs");
                res[2] = rs.getString("protein");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(Arrays.toString(res));
        
        return res;
    }
    
    // returns total calorie consumption for the day
    public String getFoodTotal(String date) {
        String res = "";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select food from totals where date='" + date + "'");
            
            if (rs.next()) 
                res = rs.getString("food");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Total food " + res);
        
        return res;
    }
    
    // returns total calories spent for the day
    public String getSportTotal(String date) {
        String res = "";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select sport from totals where date='" + date + "'");
            
            if (rs.next()) 
                res = rs.getString("sport");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Total sport " + res);
        
        return res;
    }
    
    // prints everything from food table
    public void printAllFood() {
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
    
    // prints everything from sport table
    public void printAllSport() {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from sport");
            while(rs.next())
                System.out.println("Date: " + rs.getString("date") + "Name: " + rs.getString("name") + ", cal: " 
                        + rs.getString("cal"));
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    // prints everything from totals table
    public void printAllTotals() {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals");
            while(rs.next())
                System.out.println("TOTALS Date: " + rs.getString("date") + " Goal: " + rs.getString("goal") + " Food: " +
                        rs.getString("food") + " Sport: " + rs.getString("sport") + " F: " + rs.getString("fat") + 
                        " C: " + rs.getString("carbs") + " P: " + rs.getString("protein"));
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    /*
    // prints alldate specific entries from food table
    public void printTodayFood(String date) {
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
    */
    
    // returns a string of all food entries for the date
    // name + calories
    public String getTodayFood(String date) {
        
        String str = "";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from food where date='" + date + "'");


            while(rs.next()) {
                // testing
                System.out.println("");
                System.out.format("%20s", rs.getString(2) + " | ");
                System.out.format("%5s", rs.getString(3) + " | ");

                str += rs.getString(2);
                str += ".....";
                str += rs.getString(3);
                str += "\n";
                }
        } catch  (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return str;
    }
    
    // returns a string of all sport entries for the date
    // name + calories
    public String getTodaySport(String date) {
        
        String str = "";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from sport where date='" + date + "'");


            while(rs.next()) {
                // testing
                System.out.println("");
                System.out.format("%20s", rs.getString(2) + " | ");
                System.out.format("%5s", rs.getString(3) + " | ");

                str += rs.getString(2);
                str += ".....";
                str += rs.getString(3);
                str += "\n";


            }
        } catch  (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return str;
    }
    /*
    public void getFoodMatrix(String date) {
        int lines = 0;
        
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select name, cal, fat, carbs, protein from food where date='" + date + "'");
            
            if (rs.next()) {
                rs.last();
                lines = rs.getRow();
                rs.beforeFirst();
                
                Object[][] result = new Object[lines][5];
            
            
            while (rs.next()) {
                ++lines;
                String name = "record" + lines;
                
            }
            }
            
        } catch () {
            
        }
    }
    */
    
    public ResultSet getFood(String date) {
        ResultSet rs = null;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("select name, cal, fat, carbs, protein from food where date='" + date + "'");
        } catch  (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return rs;
            
    }
    
    
    // get today's date in sql-friendly format
    public String getDate() {
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        String date = "";
        date += day +"."+(month+1)+"."+ year;
        
        
        return date;
    }
    
    
    
}
