/*
 * http://www.dreamincode.net/forums/topic/25042-creating-a-calendar-viewer-application/
 */
package myfitdayapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MyCalendar extends javax.swing.JDialog {
    static JLabel lblMonth;
    static JButton btnPrev, btnNext, btnChoose, btnCancel;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static JPanel pnlCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    //static JLabel day;
    public static String date;

    public MyCalendar(JFrame parent, boolean modal) {
        super(parent, modal);
        
       
        
        setSize(300, 400);
        setLayout(null);
	setLookAndFeel();
	createControls();
	//setBorder();
	registerActionListeners();
	addControls();
	setBounds();
	setDate();
	addHeaders();
	setBackground();
	setTableProperties();
	populateTable();
	refreshCalendar(realMonth, realYear);
        
    }
    
    private void setLookAndFeel() {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
	} catch (InstantiationException e) {
	} catch (IllegalAccessException e) {
	} catch (UnsupportedLookAndFeelException e) {
	}
        
        setUndecorated(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        
    }
    
    
    private void createControls() {
        
        pane = this.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
            
	lblMonth = new JLabel("January");
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
	
	cmbYear = new JComboBox();
	btnPrev = new JButton("<");
        btnPrev.setFocusPainted(false);
	btnNext = new JButton(">");
        btnChoose = new JButton("Choose");
        btnChoose.setEnabled(true);
        btnCancel = new JButton("Close");
        btnCancel.setEnabled(true);
        
	mtblCalendar = new DefaultTableModel() {
	    public boolean isCellEditable(int rowIndex, int mColIndex) {
		return false;
	    }
	};
        
	tblCalendar = new JTable(mtblCalendar);
	stblCalendar = new JScrollPane(tblCalendar);
        
        
    }
    
    
    private void registerActionListeners() {
	btnPrev.addActionListener(new btnPrev_Action());
	btnNext.addActionListener(new btnNext_Action());
	cmbYear.addActionListener(new cmbYear_Action());
        
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });
        
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        
    }
    
    private void addControls() {
	pane.add(lblMonth);
        pane.add(cmbYear);
        pane.add(btnPrev);
        pane.add(btnNext);
        pane.add(stblCalendar);
        pane.add(btnChoose);
        pane.add(btnCancel);
        
    }

    private void setBounds() {
	//pane.setBounds(0, 0, 320, 335);
	//lblMonth.setBounds(85 - lblMonth.getPreferredSize().width / 2, 25, 100, 25);
        lblMonth.setBounds(60, 25, 85, 25);
        cmbYear.setBounds(205, 25, 90, 20);
	btnPrev.setBounds(10, 25, 50, 25);
	btnNext.setBounds(145, 25, 50, 25);
	stblCalendar.setBounds(10, 70, 280, 250);
        btnChoose.setBounds(10, 350, 90, 29);
        btnCancel.setBounds(200, 350, 90, 29);
        
    }
    
    private void setDate() {
	GregorianCalendar cal = new GregorianCalendar(); // Create calendar
	realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
	realMonth = cal.get(GregorianCalendar.MONTH); // Get month
	realYear = cal.get(GregorianCalendar.YEAR); // Get year
	currentMonth = realMonth; // Match month and year
	currentYear = realYear;
    }

    private void addHeaders() {
	String[] headers = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
	for (int i = 0; i < 7; i++) {
	    mtblCalendar.addColumn(headers[i]);
	}
    }

    private void setBackground() {
	tblCalendar.getParent().setBackground(tblCalendar.getBackground());
    }
    
    private void setTableProperties() {
	// No resize/reorder
	tblCalendar.getTableHeader().setResizingAllowed(false);
	tblCalendar.getTableHeader().setReorderingAllowed(false);

	// Single cell selection
	tblCalendar.setColumnSelectionAllowed(true);
	tblCalendar.setRowSelectionAllowed(true);
	tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	// Set row/column count
	tblCalendar.setRowHeight(38);
	mtblCalendar.setColumnCount(7);
	mtblCalendar.setRowCount(6);
    }

    private void populateTable() {
	for (int i = realYear - 100; i <= realYear + 100; i++) {
	    cmbYear.addItem(String.valueOf(i));
	}
    }
    
    private void btnCancelActionPerformed (java.awt.event.ActionEvent evt) {
        date = null;
        dispose();
    }
    
    private void btnChooseActionPerformed (java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        //lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        lblMonth.setBounds(60, 25, 85, 25);
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i=0; i<6; i++){
                for (int j=0; j<7; j++){
                        mtblCalendar.setValueAt(null, i, j);
                }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 0);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);



        //Draw calendar
        for (int i=1; i<=nod; i++){
                int row = new Integer((i+som-2)/7);
                int column  =  (i+som-2)%7;
                mtblCalendar.setValueAt(i, row, column);
        }

        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }

    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            super.setHorizontalAlignment(CENTER);
            if (column == 5 || column == 6){ //Week-end
                    setBackground(new Color(255, 210, 210));
            }
            else{ //Week
                    setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                    if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                            setBackground(new Color(220, 220, 125));
                    }
            }
            if (selected) {
                setBackground(new Color(175,230,230));
                int mm = currentMonth+1;
                date = value.toString()+"."+mm+"."+currentYear;
                //System.out.println(date);
            }
            setBorder(null);
            setForeground(Color.black);
            return this;  
        }
        
    }

    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
                if (currentMonth == 0){ //Back one year
                        currentMonth = 11;
                        currentYear -= 1;
                }
                else{ //Back one month
                        currentMonth -= 1;
                }
                refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
                if (currentMonth == 11){ //Foward one year
                        currentMonth = 0;
                        currentYear += 1;
                }
                else{ //Foward one month
                        currentMonth += 1;
                }
                refreshCalendar(currentMonth, currentYear);
        }
    }
    
    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
                if (cmbYear.getSelectedItem() != null){
                        String b = cmbYear.getSelectedItem().toString();
                        currentYear = Integer.parseInt(b);
                        refreshCalendar(currentMonth, currentYear);
                }
        }
    }
    
    
}
