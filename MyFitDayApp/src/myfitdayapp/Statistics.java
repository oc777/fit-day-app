/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author olgachristensen
 */
public class Statistics extends javax.swing.JFrame {

    private DBHandler dbh;
    private String endDate;
    private String startDate;
    private boolean week;
    private DateFormat dateFormat;
    private Calendar cal;
    private int[] macros;
    private int[] calories;
    private String[] dates;
    private int[] goal;
    private int[] total;
    private boolean totalsEmpty;
    private PieChart chartMacros;
    private PieChartCals chartCals;
    private BarChart chartTotals;
    
    private static Statistics obj = null;
    
    private Statistics(String today) {
        dbh = new DBHandler();
        endDate = today;
        week = true;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        
        macros = new int[3];
        calories = new int[2];
        
        totalsEmpty = true;
        
        
        getContentPane().setBackground(new Color(51, 51, 51));
        initComponents();
        
        
        lblEndDate.setText(endDate);
        setStartDate(-1);
        getTotals();
        addChartMacros();
        addChartCalories();
        addChart();
        
        setLocationRelativeTo(null);
    }
    
    public static Statistics getObj(String date) {
        if (obj == null)
            obj = new Statistics(date);
        
        return obj;
    }
    
    // redraw chart with new data
    private void updateCharts() {
        
        // update macros chart
        pnlChartMacros.removeAll();
        pnlChartMacros.revalidate();
        addChartMacros();
        pnlChartMacros.repaint();
        
        // update calories chart
        pnlChartCals.removeAll();
        pnlChartCals.revalidate();
        addChartCalories();
        pnlChartCals.repaint();
        
        // updait main chart
        pnlChart.removeAll();
        pnlChart.revalidate();
        addChart();
        pnlChart.repaint();
        
    }
    
    private void setStartDate(int x) {
        try {
                cal.setTime(dateFormat.parse(endDate));
            } catch (ParseException ex) {
                System.out.println("parse date fail " + ex);
            }
        
        if (week){
            if (x < 0) 
                cal.add(Calendar.DATE, -6);
            else 
                cal.add(Calendar.DATE, 1);
            
            startDate = dateFormat.format(cal.getTime());
            //System.out.println(startDate);
        }
        else {
            if (x < 0)
                cal.add(Calendar.MONTH, -1);
            else
                cal.add(Calendar.DATE, 0);
            
            startDate = dateFormat.format(cal.getTime());
            //System.out.println("start "+startDate);
        }
        
        lblStartDate.setText(startDate);
            
    }
    
    private void setEndDate(int x) {
        try {
                cal.setTime(dateFormat.parse(startDate));
            } catch (ParseException ex) {
                System.out.println("parse date fail " + ex);
            }
        
        if (x < 0)
            cal.add(Calendar.DATE, -1);
        else {
            if (week)
                cal.add(Calendar.DATE, 6);
            else
                cal.add(Calendar.MONTH, 1);
        }
            
        endDate = dateFormat.format(cal.getTime());
        //System.out.println("end "+endDate);
        
        lblEndDate.setText(endDate);
    }
    
    private void getMacros() {
        macros = dbh.getMacrosStats(startDate, endDate);
    }
    
    private void getCalories() {
        calories = dbh.getCaloriesStats(startDate, endDate);
    }
    
    private void getTotals() {
        dates = dbh.getDatesChart(startDate, endDate);
        goal = dbh.getGoalChart(startDate, endDate);
        total = dbh.getTotalChart(startDate, endDate);
        
        checkTotals();
    }
    
    private void checkTotals() {
        totalsEmpty = true;
        
        for (int i = 0; i < total.length; i++) {
            if (total[i] != 0) {
                totalsEmpty = false;
                break;
            }
        }
            
    }
    
    
    private void addChartMacros() {
        getMacros();
        
        if (macros[0] == 0 && macros[1] == 0 && macros[2] == 0) {
            chartMacros = new PieChart();
        }
        else {
            int fat = macros[0];
            int carbs = macros[1];
            int protein = macros[2];
            
            chartMacros = new PieChart(fat, carbs, protein);
        }
        
        pnlChartMacros.add(chartMacros.panel);
        pnlChartMacros.validate();
        
        txtFat.setText(""+ macros[0]);
        txtCarbs.setText("" + macros[1]);
        txtProtein.setText("" + macros[2]);
        
    }
    
    
    private void addChartCalories() {
        getCalories();
        
        if (calories[0] == 0 && calories[1] == 0) {
            chartCals = new PieChartCals();
        }
        else {
            int food = calories[0];
            int sport = calories[1];
            
            chartCals = new PieChartCals(food, sport);
        }
        
        pnlChartCals.add(chartCals.panel);
        pnlChartCals.validate();
        
        txtSport.setText("" + calories[1]);
        txtFood.setText("" + calories[0]);
        
    }
    
    private void addChart() {
        getTotals();
        
        System.out.println(Arrays.toString(total));
        System.out.println(totalsEmpty);
        
        if (totalsEmpty || total.length == 0)
            chartTotals = new BarChart();
        else
            chartTotals = new BarChart(dates, goal, total);
        
        pnlChart.add(chartTotals.panel);
        pnlChart.validate();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEndDate = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        pnlChartCals = new javax.swing.JPanel();
        pnlChartMacros = new javax.swing.JPanel();
        pnlChart = new javax.swing.JPanel();
        pnlProtein = new javax.swing.JPanel();
        pnlCarbs = new javax.swing.JPanel();
        pnlFat = new javax.swing.JPanel();
        btnWeek = new javax.swing.JButton();
        btnMonth = new javax.swing.JButton();
        lblStartDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblFat = new javax.swing.JLabel();
        lblProtein = new javax.swing.JLabel();
        lblCarbs = new javax.swing.JLabel();
        txtFat = new javax.swing.JLabel();
        txtCarbs = new javax.swing.JLabel();
        txtProtein = new javax.swing.JLabel();
        pnlFat1 = new javax.swing.JPanel();
        pnlCarbs1 = new javax.swing.JPanel();
        lblFood1 = new javax.swing.JLabel();
        lblFood2 = new javax.swing.JLabel();
        pnlSport = new javax.swing.JPanel();
        lblSport1 = new javax.swing.JLabel();
        txtSport = new javax.swing.JLabel();
        txtFood = new javax.swing.JLabel();
        lblFood = new javax.swing.JLabel();
        pnlFood = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblEndDate.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblEndDate.setForeground(new java.awt.Color(255, 153, 0));
        lblEndDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEndDate.setText("5.05.2016");
        lblEndDate.setMaximumSize(new java.awt.Dimension(80, 29));
        lblEndDate.setMinimumSize(new java.awt.Dimension(80, 29));
        lblEndDate.setPreferredSize(new java.awt.Dimension(80, 29));

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        pnlChartCals.setBackground(new java.awt.Color(204, 255, 204));
        pnlChartCals.setPreferredSize(new java.awt.Dimension(195, 195));

        javax.swing.GroupLayout pnlChartCalsLayout = new javax.swing.GroupLayout(pnlChartCals);
        pnlChartCals.setLayout(pnlChartCalsLayout);
        pnlChartCalsLayout.setHorizontalGroup(
            pnlChartCalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );
        pnlChartCalsLayout.setVerticalGroup(
            pnlChartCalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        pnlChartMacros.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout pnlChartMacrosLayout = new javax.swing.GroupLayout(pnlChartMacros);
        pnlChartMacros.setLayout(pnlChartMacrosLayout);
        pnlChartMacrosLayout.setHorizontalGroup(
            pnlChartMacrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );
        pnlChartMacrosLayout.setVerticalGroup(
            pnlChartMacrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        pnlChart.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout pnlChartLayout = new javax.swing.GroupLayout(pnlChart);
        pnlChart.setLayout(pnlChartLayout);
        pnlChartLayout.setHorizontalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        pnlChartLayout.setVerticalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        pnlProtein.setBackground(new java.awt.Color(222, 0, 33));
        pnlProtein.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlProteinLayout = new javax.swing.GroupLayout(pnlProtein);
        pnlProtein.setLayout(pnlProteinLayout);
        pnlProteinLayout.setHorizontalGroup(
            pnlProteinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlProteinLayout.setVerticalGroup(
            pnlProteinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        pnlCarbs.setBackground(new java.awt.Color(177, 192, 41));
        pnlCarbs.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlCarbsLayout = new javax.swing.GroupLayout(pnlCarbs);
        pnlCarbs.setLayout(pnlCarbsLayout);
        pnlCarbsLayout.setHorizontalGroup(
            pnlCarbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlCarbsLayout.setVerticalGroup(
            pnlCarbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        pnlFat.setBackground(new java.awt.Color(135, 198, 197));
        pnlFat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlFat.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlFatLayout = new javax.swing.GroupLayout(pnlFat);
        pnlFat.setLayout(pnlFatLayout);
        pnlFatLayout.setHorizontalGroup(
            pnlFatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlFatLayout.setVerticalGroup(
            pnlFatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        btnWeek.setText("Week");
        btnWeek.setEnabled(false);
        btnWeek.setMaximumSize(new java.awt.Dimension(84, 29));
        btnWeek.setMinimumSize(new java.awt.Dimension(84, 29));
        btnWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWeekActionPerformed(evt);
            }
        });

        btnMonth.setText("Month");
        btnMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonthActionPerformed(evt);
            }
        });

        lblStartDate.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(255, 153, 0));
        lblStartDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStartDate.setText("5.05.2016");
        lblStartDate.setMaximumSize(new java.awt.Dimension(80, 29));
        lblStartDate.setMinimumSize(new java.awt.Dimension(80, 29));
        lblStartDate.setPreferredSize(new java.awt.Dimension(80, 29));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("-");
        jLabel1.setMaximumSize(new java.awt.Dimension(7, 29));
        jLabel1.setMinimumSize(new java.awt.Dimension(7, 29));
        jLabel1.setPreferredSize(new java.awt.Dimension(7, 29));

        lblFat.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFat.setForeground(new java.awt.Color(255, 255, 255));
        lblFat.setText("Fat:");

        lblProtein.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblProtein.setForeground(new java.awt.Color(255, 255, 255));
        lblProtein.setText("Protein:");

        lblCarbs.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblCarbs.setForeground(new java.awt.Color(255, 255, 255));
        lblCarbs.setText("Carbs:");

        txtFat.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtFat.setForeground(new java.awt.Color(255, 153, 0));
        txtFat.setText("jLabel4");

        txtCarbs.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtCarbs.setForeground(new java.awt.Color(255, 153, 0));
        txtCarbs.setText("jLabel4");

        txtProtein.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtProtein.setForeground(new java.awt.Color(255, 153, 0));
        txtProtein.setText("jLabel4");

        pnlFat1.setBackground(new java.awt.Color(135, 198, 197));
        pnlFat1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlFat1.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlFat1Layout = new javax.swing.GroupLayout(pnlFat1);
        pnlFat1.setLayout(pnlFat1Layout);
        pnlFat1Layout.setHorizontalGroup(
            pnlFat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlFat1Layout.setVerticalGroup(
            pnlFat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        pnlCarbs1.setBackground(new java.awt.Color(220, 220, 125));
        pnlCarbs1.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlCarbs1Layout = new javax.swing.GroupLayout(pnlCarbs1);
        pnlCarbs1.setLayout(pnlCarbs1Layout);
        pnlCarbs1Layout.setHorizontalGroup(
            pnlCarbs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlCarbs1Layout.setVerticalGroup(
            pnlCarbs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        lblFood1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFood1.setForeground(new java.awt.Color(255, 255, 255));
        lblFood1.setText("Goal, calories");

        lblFood2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFood2.setForeground(new java.awt.Color(255, 255, 255));
        lblFood2.setText("Total calories");

        pnlSport.setBackground(new java.awt.Color(222, 0, 33));
        pnlSport.setToolTipText("");
        pnlSport.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlSportLayout = new javax.swing.GroupLayout(pnlSport);
        pnlSport.setLayout(pnlSportLayout);
        pnlSportLayout.setHorizontalGroup(
            pnlSportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlSportLayout.setVerticalGroup(
            pnlSportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        lblSport1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblSport1.setForeground(new java.awt.Color(255, 255, 255));
        lblSport1.setText("Sport:");

        txtSport.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtSport.setForeground(new java.awt.Color(255, 153, 0));
        txtSport.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtSport.setText("jLabel4");
        txtSport.setSize(new java.awt.Dimension(62, 23));

        txtFood.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtFood.setForeground(new java.awt.Color(255, 153, 0));
        txtFood.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtFood.setText("jLabel5");
        txtFood.setSize(new java.awt.Dimension(62, 23));

        lblFood.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFood.setForeground(new java.awt.Color(255, 255, 255));
        lblFood.setText("Meals:");

        pnlFood.setBackground(new java.awt.Color(177, 192, 41));
        pnlFood.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout pnlFoodLayout = new javax.swing.GroupLayout(pnlFood);
        pnlFood.setLayout(pnlFoodLayout);
        pnlFoodLayout.setHorizontalGroup(
            pnlFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        pnlFoodLayout.setVerticalGroup(
            pnlFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChartCals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFood2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCarbs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFood1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFood))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblSport1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFood)
                            .addComponent(txtSport))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlChartMacros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFat))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlProtein, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProtein))
                    .addComponent(pnlCarbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblCarbs)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProtein)
                    .addComponent(txtCarbs, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFat, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMonth)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMonth)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlChartMacros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnlChartCals, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pnlFat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFat))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pnlCarbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCarbs))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pnlProtein, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblProtein)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtFat)
                                                .addGap(41, 41, 41))
                                            .addComponent(txtCarbs))
                                        .addGap(18, 18, 18)
                                        .addComponent(txtProtein)))
                                .addGap(94, 94, 94))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pnlSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSport1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pnlFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFood)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSport)
                                .addGap(18, 18, 18)
                                .addComponent(txtFood)))
                        .addGap(137, 137, 137)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlFat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFood2)
                    .addComponent(lblFood1)
                    .addComponent(pnlCarbs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthActionPerformed
        week = false;
        btnMonth.setEnabled(false);
        btnWeek.setEnabled(true);
        
        setStartDate(-1);
        updateCharts();
    }//GEN-LAST:event_btnMonthActionPerformed

    private void btnWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeekActionPerformed
        week = true;
        btnMonth.setEnabled(true);
        btnWeek.setEnabled(false);
        
        setStartDate(-1);
        updateCharts();
    }//GEN-LAST:event_btnWeekActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        setEndDate(-1);
        setStartDate(-1);
        
        updateCharts();
        
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        setStartDate(1);
        setEndDate(1);
        
        updateCharts();
        
    }//GEN-LAST:event_btnNextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Statistics("2016-05-28").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMonth;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnWeek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCarbs;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblFat;
    private javax.swing.JLabel lblFood;
    private javax.swing.JLabel lblFood1;
    private javax.swing.JLabel lblFood2;
    private javax.swing.JLabel lblProtein;
    private javax.swing.JLabel lblSport1;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JPanel pnlCarbs;
    private javax.swing.JPanel pnlCarbs1;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlChartCals;
    private javax.swing.JPanel pnlChartMacros;
    private javax.swing.JPanel pnlFat;
    private javax.swing.JPanel pnlFat1;
    private javax.swing.JPanel pnlFood;
    private javax.swing.JPanel pnlProtein;
    private javax.swing.JPanel pnlSport;
    private javax.swing.JLabel txtCarbs;
    private javax.swing.JLabel txtFat;
    private javax.swing.JLabel txtFood;
    private javax.swing.JLabel txtProtein;
    private javax.swing.JLabel txtSport;
    // End of variables declaration//GEN-END:variables
}
