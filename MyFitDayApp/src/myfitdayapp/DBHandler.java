package myfitdayapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
            
            DatabaseMetaData dmd = connection.getMetaData();
            
            ResultSet rs = dmd.getTables(null, null, null, null);
            if (!rs.next()) 
                createTable();
            
            
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
            
            // list of foods - NOT USED AT THE MOMENT
            connection.createStatement().execute("create table foodlist (id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(20), cal int, fat int, carbs int, protein int)");    
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // EditFood - add new record
    // stores entry in food table
    // updates totals
    // updates macros
    public void insertDataFood(String date, String name, int cal, int f, int c, int p) {
        try {
            connection.createStatement().execute("insert into food values('"
                    +date+"','"+name+"',"+cal+","+f+","+c+","+p+")");
            
            updateTotalsFood(date);
            
            macros = getMacros(date);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // EditSport - add new record
    // stores entry in sport table
    public void insertDataSport(String date, String name, int cal) {
        try {
            connection.createStatement().execute("insert into sport values('"
                    +date+"','"+name+"',"+cal+")");
            
            updateTotalsSport(date);
            
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
    
    // returns last available goal from totals
    public int latestGoal() {
        int goal = 0;
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from totals"); 

            if (rs.last()) 
                goal = rs.getInt("goal");
             
        
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return goal;
    }
    
    // checks if any entry for the date was made in totals
    // returns true if minimum one entry exists 
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
    // returns true if minimum one entry exists 
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
    // returns true if minimum one entry exists 
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
    
    
    // calculates totals for food (for the whole day)
    // updates totals table
    public void updateTotalsFood(String date) {
        try {
            Statement st = connection.createStatement();
            
            ResultSet rsFood = st.executeQuery("select * from food where date='" + date + "'");
            
            int cal = 0;
            int fat = 0;
            int carb = 0;
            int prot = 0;
            
            while (rsFood.next()) {
                cal += Integer.parseInt(rsFood.getString("cal"));
                fat += Integer.parseInt(rsFood.getString("fat"));
                carb += Integer.parseInt(rsFood.getString("carbs"));
                prot += Integer.parseInt(rsFood.getString("protein"));
            }
            
            ResultSet rsTotals = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rsTotals.next()) {
                st.execute("update totals set food="+cal+",fat="+fat+",carbs="+carb+",protein="+prot 
                        +" where date='" + date + "'");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // calculates totals for sport (for the whole day)
    // updates totals table
    public void updateTotalsSport(String date) {
        
        try{
            Statement st = connection.createStatement();
            
            ResultSet rsSport = st.executeQuery("select * from sport where date='" + date + "'");
            
            int cal = 0;
            
            while (rsSport.next()) {
                cal += Integer.parseInt(rsSport.getString("cal"));
            }
            
            ResultSet rsTotals = st.executeQuery("select * from totals where date='" + date + "'");
            
            if (rsTotals.next()) {
                st.execute("update totals set sport="+cal+" where date='" + date + "'");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // returns macros for the date in form of array of strings
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
                
        return res;
    }
    
    // returns total calorie consumption for the date
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
                
        return res;
    }
    
    // returns total calories spent for the date
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
                
        return res;
    }
    
    
    // EditFood - result set to build the table 
    public ResultSet getFoodTable(String date) {
        ResultSet rs = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select name, cal, fat, carbs, protein from food where date='" + date + "'");
        } catch  (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return rs;
            
    }
    
    
    // EditFood - update record 
    public void updateFoodEdit(String date, int index, String[] data) {
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select name, cal, fat, carbs, protein from food where date='" + date + "'");
            
            int cal = Integer.parseInt(data[1]);
            int f = Integer.parseInt(data[2]);
            int c = Integer.parseInt(data[3]);
            int p = Integer.parseInt(data[4]);
            
            int i = 0;
            while(rs.next()) {
                // find the row to update
                if (i == index) {
                    rs.updateString("name", data[0]);
                    rs.updateInt("cal", cal);
                    rs.updateInt("fat", f);
                    rs.updateInt("carbs", c);
                    rs.updateInt("protein", p);
                    
                    rs.updateRow();
                    
                    break;
                }
                i++;
            }
            
            updateTotalsFood(date);
            
            macros = getMacros(date);
        
        } catch (SQLException e) {
            System.out.println("Update DB fail: " + e);
        } 
        
    }
    
    // EditFood - delete record
    public void updateFoodDelete(String date, int index) {
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from food where date='" + date + "'");
            
            int i = 0;
            while(rs.next()) {
                // find the row to delete
                if (i == index) {
                    rs.deleteRow();
                    
                    break;
                }
                i++;
            }
            
            updateTotalsFood(date);
            
            macros = getMacros(date);
            
            
        } catch (SQLException e) {
            System.out.println("updateFoodDelete fail " + e);
        }
        
        
    }
    
    // EditSport - result set to build the table 
    public ResultSet getSportTable(String date) {
        ResultSet rs = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select name, cal from sport where date='" + date + "'");
        } catch  (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return rs;
            
    }
    
    // EditSport - update record 
    public void updateSportEdit(String date, int index, String[] data) {
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from sport where date='" + date + "'");
            
            int cal = Integer.parseInt(data[1]);
            
            int i = 0;
            while(rs.next()) {
                // find the row to update
                if (i == index) {
                    rs.updateString("name", data[0]);
                    rs.updateInt("cal", cal);
                    
                    rs.updateRow();
                    
                    break;
                }
                i++;
            }
            
            updateTotalsSport(date);
                    
        } catch (SQLException e) {
            System.out.println("Update DB fail: " + e);
        } 
        
        
    }
    
    // EditSport - delete record
    public void updateSportDelete(String date, int index) {
        
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from sport where date='" + date + "'");
            
            int i = 0;
            while(rs.next()) {
                // find row to delete
                if (i == index) {
                    rs.deleteRow();
                    
                    break;
                }
                i++;
            }
            
            updateTotalsSport(date);
                        
            
        } catch (SQLException e) {
            System.out.println("updateSportDelete fail " + e);
        }
        
        
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
    
    
    // Statistics - calculate macros for a specific period
    public int[] getMacrosStats(String startDate, String endDate) {
        int[] macrosStats = new int[3];     // fat, carbs, protein
        int fat = 0;
        int carbs = 0;
        int p = 0;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date between '"+startDate+"' and '"+endDate+"'");
            
            // calculate total macros
            while (rs.next()) {
                if (rs.getString("fat") != null)
                    fat += Integer.parseInt(rs.getString("fat"));
                if (rs.getString("carbs") != null)
                    carbs += Integer.parseInt(rs.getString("carbs"));
                if (rs.getString("protein") != null)
                    p += Integer.parseInt(rs.getString("protein"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // fill the array with data
        macrosStats[0] = fat;
        macrosStats[1] = carbs;
        macrosStats[2] = p;
        
        return macrosStats;
    }
    
    // Statistics - calculate calorie for a specific period
    public int[] getCaloriesStats(String startDate, String endDate) {
        int[] calStats = new int[2];    // fod, sport
        int food = 0;
        int sport = 0;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date between '"+startDate+"' and '"+endDate+"'");
            
            // calculate total consumption and activities
            while (rs.next()) {
                if (rs.getString("food") != null)
                    food += Integer.parseInt(rs.getString("food"));
                if (rs.getString("sport") != null)
                    sport += Integer.parseInt(rs.getString("sport"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // fill the array with data
        calStats[0] = food;
        calStats[1] = sport;
        
        return calStats;
    }

    // BarChart - get list of dates available in the specific period
    public ArrayList<String> getDatesChart(String startDate, String endDate) {
        ArrayList<String> list = new ArrayList<>();
        
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date between '"+startDate+"' and '"+endDate+"'");
            
            while(rs.next()) {
                list.add(rs.getString("date"));
            }
            
            
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // copy data from List to Array
        //String[] dates = list.toArray(new String[list.size()]);
        
        return list;
    }
    
    // BarChart - get list of goals available in the specific period
    public ArrayList<Integer> getGoalChart(String startDate, String endDate) {
        ArrayList<Integer> list = new ArrayList<>();
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date between '"+startDate+"' and '"+endDate+"'");
            
            while(rs.next()) {
                list.add(rs.getInt("goal"));
            }
        
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        int[] goal = new int[list.size()];
        // copy data from List to Array
        for (int i = 0; i < goal.length; i++)
            goal[i] = list.get(i);
        */
        
        return list;
    }
    
    // BarChart - get list of total cals available in the specific period
    public ArrayList<Integer> getTotalChart (String startDate, String endDate) {
        ArrayList<Integer> list = new ArrayList<>();
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from totals where date between '"+startDate+"' and '"+endDate+"'");
        
            while(rs.next()) {
                int f = rs.getInt("food");
                int s = rs.getInt("sport");
                int t = f - s;      // total calories = consumed - burned
                list.add(t);
            }
        
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        int[] total = new int[list.size()];
        // copy data from List to Array
        for (int i = 0; i < total.length; i++)
            total[i] = list.get(i);
        */
        return list;
    }
    
}
