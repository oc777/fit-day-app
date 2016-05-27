/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author olgachristensen
 */
public class EditSport extends javax.swing.JDialog {

    private DBHandler dbh;
    private ResultSet rs;
    public String date;
    private int selectedRow;
    private DefaultTableModel dtm;
    
    /**
     * Creates new form EditSport
     */
    public EditSport(String d, JFrame parent, boolean modal) {
        super(parent, modal);
        
        dbh = new DBHandler();
        rs = dbh.getSportTable(d);
        date = d;
        selectedRow = 0;
        
        setUndecorated(true);
        initComponents();
        showDateLable.setText(d);
        setColWidth();
        
    }
    
    private void setColWidth() {
        sportTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        sportTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        sportTable = new javax.swing.JTable(buildTable(rs)) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        btnClose = new javax.swing.JButton();
        nameLable = new javax.swing.JLabel();
        calLable = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        calField = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        dateLable = new javax.swing.JLabel();
        showDateLable = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        sportTable.setSelectionBackground(new java.awt.Color(153, 153, 153));
        sportTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sportTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sportTable);

        btnClose.setText("Close");
        btnClose.setMaximumSize(new java.awt.Dimension(80, 29));
        btnClose.setMinimumSize(new java.awt.Dimension(80, 29));
        btnClose.setPreferredSize(new java.awt.Dimension(80, 29));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        nameLable.setText("Name:");

        calLable.setText("Calories:");

        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameFieldKeyTyped(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.setMaximumSize(new java.awt.Dimension(90, 29));
        btnUpdate.setMinimumSize(new java.awt.Dimension(90, 29));
        btnUpdate.setPreferredSize(new java.awt.Dimension(90, 29));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.setMaximumSize(new java.awt.Dimension(80, 29));
        btnDelete.setMinimumSize(new java.awt.Dimension(80, 29));
        btnDelete.setPreferredSize(new java.awt.Dimension(80, 29));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        dateLable.setText("Date:");

        showDateLable.setText("today");

        btnAdd.setText("Add");
        btnAdd.setEnabled(false);
        btnAdd.setMaximumSize(new java.awt.Dimension(80, 29));
        btnAdd.setMinimumSize(new java.awt.Dimension(80, 29));
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 29));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sport.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("ACTIVITY LOG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(dateLable)
                                            .addGap(22, 22, 22))
                                        .addComponent(calLable, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(nameLable))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(calField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(showDateLable)
                                    .addComponent(nameField))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLable)
                            .addComponent(showDateLable))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLable)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(calLable)
                            .addComponent(calField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        int q = JOptionPane.showConfirmDialog(this, "Delete chosen record?", 
                "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (q == 0) {
            dtm.removeRow(selectedRow);
            dbh.updateSportDelete(date, selectedRow);
            emptyTextBoxes();
            removeBorder();
            disableButtons();
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void sportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sportTableMouseClicked
        
        enableButtons();
        removeBorder();
        
        selectedRow = sportTable.getSelectedRow();
        //System.out.println(selectedRow);
        
        String n = sportTable.getModel().getValueAt(selectedRow, 0).toString();
        nameField.setText(n);
            
        String cal = sportTable.getModel().getValueAt(selectedRow, 1).toString();
        calField.setText(cal);
     
    }//GEN-LAST:event_sportTableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        Border border = BorderFactory.createLineBorder(Color.RED, 1);       
        removeBorder();
            
            
            try {
                if (nameField.getText().length() != 0) {
                    
                    sportTable.setValueAt(nameField.getText(), selectedRow, 0);
                    sportTable.setValueAt(calField.getText(), selectedRow, 1);

                    String[] data = {nameField.getText(), calField.getText()};

                    dbh.updateSportEdit(date, selectedRow, data);

                    emptyTextBoxes();
                    disableButtons();

                }
                else
                    nameField.setBorder(border);

            }
            catch (NumberFormatException e) {
                calField.setBorder(border);
            }
        
      
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void removeBorder() {
        nameField.setBorder(null);
        calField.setBorder(null);
    }
    
    private void nameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyTyped
        btnAdd.setEnabled(true);

        // limits the text input to 20 chars
        if (nameField.getText().length() >= 20)
            evt.consume();
    }//GEN-LAST:event_nameFieldKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Border border = BorderFactory.createLineBorder(Color.RED, 1);
        removeBorder();
        
        try {
            if (nameField.getText().length() != 0) {
                Vector<Object> data = new Vector<Object>();
                
                int cal = Integer.parseInt(calField.getText());

                data.add(nameField.getText());
                data.add(cal);
                
                dtm.addRow(data);
                dbh.insertDataSport(dbh.getDate(), nameField.getText(), cal);

                emptyTextBoxes();
                disableButtons();
                
            
            } 
            else {
                nameField.setBorder(border);
            }
        } catch (NumberFormatException e) {
            //errorMsg.setText("Only integers");
            calField.setBorder(border);
        } 
    }//GEN-LAST:event_btnAddActionPerformed

    
    private void emptyTextBoxes() {
        nameField.setText("");
        calField.setText("");
        
    }
    
    private void disableButtons() {
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
    }
    
    private void enableButtons() {
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
    }
    
    
    public DefaultTableModel buildTable(ResultSet rs)  {
        
        Vector<String> columnNames = new Vector<String>();
        
        columnNames.add("Name");
        columnNames.add("Cal");
        
        
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();

            /*
            // get names of columns
            for (int i = 1; i <= columns; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            */

            // get data for the table
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columns; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 

        dtm = new DefaultTableModel(data, columnNames);
        
        return dtm;

    }
    
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
            java.util.logging.Logger.getLogger(EditSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new EditFood().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    protected javax.swing.JTextField calField;
    private javax.swing.JLabel calLable;
    private javax.swing.JLabel dateLable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLable;
    private javax.swing.JLabel showDateLable;
    private javax.swing.JTable sportTable;
    // End of variables declaration//GEN-END:variables
}
