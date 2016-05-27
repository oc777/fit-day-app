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
    private PieChart chartMacros;
    private PieChartCals chartCals;
    
    
    public Statistics(String today) {
        dbh = new DBHandler();
        endDate = today;
        week = true;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        
        macros = new int[3];
        calories = new int[2];
        
        setStartDate();
        
        getContentPane().setBackground(new Color(51, 51, 51));
        initComponents();
        
        addChartMacros();
        addChartCalories();
        
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
        
    }
    
    private void setStartDate() {
        try {
                cal.setTime(dateFormat.parse(endDate));
            } catch (ParseException ex) {
                System.out.println("parse date fail " + ex);
            }
        
        if (week){
            cal.add(Calendar.DATE, -6);
            startDate = dateFormat.format(cal.getTime());
            System.out.println(startDate);
        }
        else {
            cal.add(Calendar.MONTH, -1);
            startDate = dateFormat.format(cal.getTime());
            System.out.println(startDate);
        }
            
    }
    
    private void getMacros() {
        macros = dbh.getMacrosStats(startDate, endDate);
    }
    
    private void getCalories() {
        calories = dbh.getCaloriesStats(startDate, endDate);
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
        pnlSport = new javax.swing.JPanel();
        pnlFood = new javax.swing.JPanel();
        pnlProtein = new javax.swing.JPanel();
        pnlCarbs = new javax.swing.JPanel();
        pnlFat = new javax.swing.JPanel();
        btnWeek = new javax.swing.JButton();
        btnMonth = new javax.swing.JButton();
        lblStartDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblFat = new javax.swing.JLabel();
        lblFood = new javax.swing.JLabel();
        txtFood = new javax.swing.JLabel();
        lblSport1 = new javax.swing.JLabel();
        lblProtein = new javax.swing.JLabel();
        lblCarbs = new javax.swing.JLabel();
        txtSport = new javax.swing.JLabel();
        txtFat = new javax.swing.JLabel();
        txtCarbs = new javax.swing.JLabel();
        txtProtein = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEndDate.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblEndDate.setForeground(new java.awt.Color(255, 153, 0));
        lblEndDate.setText("5.05.2016");
        lblEndDate.setMaximumSize(new java.awt.Dimension(80, 29));
        lblEndDate.setMinimumSize(new java.awt.Dimension(80, 29));
        lblEndDate.setPreferredSize(new java.awt.Dimension(80, 29));

        btnPrev.setText("<");

        btnNext.setText(">");

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
            .addGap(0, 869, Short.MAX_VALUE)
        );
        pnlChartLayout.setVerticalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        pnlSport.setBackground(new java.awt.Color(255, 153, 153));
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

        pnlFood.setBackground(new java.awt.Color(153, 255, 153));
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

        pnlProtein.setBackground(new java.awt.Color(255, 153, 153));
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

        pnlCarbs.setBackground(new java.awt.Color(153, 255, 153));
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

        pnlFat.setBackground(new java.awt.Color(153, 255, 255));
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

        lblFood.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFood.setForeground(new java.awt.Color(255, 255, 255));
        lblFood.setText("Meals:");

        txtFood.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtFood.setForeground(new java.awt.Color(255, 153, 0));
        txtFood.setText("jLabel5");

        lblSport1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblSport1.setForeground(new java.awt.Color(255, 255, 255));
        lblSport1.setText("Sport:");

        lblProtein.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblProtein.setForeground(new java.awt.Color(255, 255, 255));
        lblProtein.setText("Protein:");

        lblCarbs.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblCarbs.setForeground(new java.awt.Color(255, 255, 255));
        lblCarbs.setText("Carbs:");

        txtSport.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtSport.setForeground(new java.awt.Color(255, 153, 0));
        txtSport.setText("jLabel4");

        txtFat.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtFat.setForeground(new java.awt.Color(255, 153, 0));
        txtFat.setText("jLabel4");

        txtCarbs.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtCarbs.setForeground(new java.awt.Color(255, 153, 0));
        txtCarbs.setText("jLabel4");

        txtProtein.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtProtein.setForeground(new java.awt.Color(255, 153, 0));
        txtProtein.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlChartCals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                .addGap(80, 80, 80)
                                .addComponent(pnlChartMacros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(txtFat, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMonth)
                    .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlChartMacros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pnlChartCals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
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
                                    .addGap(73, 73, 73))))
                        .addGap(40, 40, 40)))
                .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthActionPerformed
        week = false;
        btnMonth.setEnabled(false);
        btnWeek.setEnabled(true);
        setStartDate();
        updateCharts();
    }//GEN-LAST:event_btnMonthActionPerformed

    private void btnWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeekActionPerformed
        week = true;
        btnMonth.setEnabled(true);
        btnWeek.setEnabled(false);
        setStartDate();
        updateCharts();
    }//GEN-LAST:event_btnWeekActionPerformed

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
                new Statistics("2016-05-21").setVisible(true);
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
    private javax.swing.JLabel lblProtein;
    private javax.swing.JLabel lblSport1;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JPanel pnlCarbs;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlChartCals;
    private javax.swing.JPanel pnlChartMacros;
    private javax.swing.JPanel pnlFat;
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
