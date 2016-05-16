/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

/**
 *
 * @author olgachristensen
 */
public class ChooseDate extends javax.swing.JFrame {
    
    private DBHandler dbh;
    public String date;
    
    /**
     * Creates new form SetGoalD
     */
    public ChooseDate(String d) {
        setUndecorated(true);
        initComponents();
        dbh = new DBHandler();
        date = d;
        defaultValues(d);
                
        
    }
    
    // preselect today day, month, year in combo boxes
    private void defaultValues(String d) {
        String[] dmy = d.split("\\.");

        String dd = dmy[0];
        int mm = Integer.parseInt(dmy[1]);
        String yy = dmy[2];
        
        dayBox.setSelectedItem(dd);
        monthBox.setSelectedIndex(mm - 1);
        yearBox.setSelectedItem(yy);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        month = new javax.swing.JLabel();
        btnChoose = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        errorMsg = new javax.swing.JLabel();
        monthBox = new javax.swing.JComboBox<>();
        day = new javax.swing.JLabel();
        dayBox = new javax.swing.JComboBox<>();
        year = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        btnToday = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        month.setText("Month:");

        btnChoose.setText("Choose");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        errorMsg.setForeground(java.awt.Color.red);

        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        day.setText("Day:");

        dayBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        year.setText("Year:");

        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2015", "2016", "2017" }));

        btnToday.setText("Today");
        btnToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(month, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day)
                            .addComponent(year))
                        .addGap(156, 156, 156))
                    .addComponent(errorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monthBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnToday, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnChoose)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(day)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(month)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(year)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(errorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChoose)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnToday)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
       String d = dayBox.getSelectedItem() + "";
       String m = monthBox.getSelectedItem() + "";
       String y = yearBox.getSelectedItem() + "";
       
       if (isDate(y, m, d)) {
           //date = year + "." + month + "." + day;
           date = d  + "." + getMonth(m) + "." + y;
           dispose();
       } 
       
       else
           errorMsg.setText("Not a date");
       
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodayActionPerformed
        date = dbh.getDate();
        dispose();
    }//GEN-LAST:event_btnTodayActionPerformed

    //checks if the date entered is correct
    private static boolean isDate(String year, String month, String day) {
		
		boolean isDate = true;
		
                int yy = Integer.parseInt(year);
                System.out.println(""+yy);
		int mm = getMonth(month);
                System.out.println(""+mm);
		int dd = Integer.parseInt(day);
                System.out.println(""+dd);
	
		//special case February
		if (yy%4 != 0 && mm == 2 && dd > 28) 
			isDate = false;
		
		if (yy%4 == 0 && mm == 2 && dd > 29) 
			isDate = false;
		
		//special case 30-day month
		if (mm == 4 || mm == 6 || mm == 9 || mm == 11) 
			if (dd > 30)
				isDate = false;
		
		return isDate;	
	}
    
    private static int getMonth(String str) {
        int result = 0;
        switch (str) {
            case "January":
                    result = 1;
                    break;
            case "February":
                    result = 2;
                    break;
            case "March":
                    result = 3;
                    break;
            case "April":
                    result = 4;
                    break;
            case "May":
                    result = 5;
                    break;
            case "June":
                    result = 6;
                    break;
            case "July":
                    result = 7;
                    break;
            case "August":
                    result = 8;
                    break;
            case "September":
                    result = 9;
                    break;
            case "October":
                    result = 10;
                    break;
            case "November":
                    result = 11;
                    break;
            case "December":
                    result = 12;
                    break;
        }
        
        return result;
    }
    
    
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SetGoalD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetGoalD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetGoalD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetGoalD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetGoalD dialog = new SetGoalD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
    }
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnToday;
    private javax.swing.JLabel day;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JLabel errorMsg;
    private javax.swing.JLabel month;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JLabel year;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables
}