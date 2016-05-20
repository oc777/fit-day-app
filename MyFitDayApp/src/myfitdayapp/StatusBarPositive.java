/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author olgachristensen
 */
public class StatusBarPositive extends javax.swing.JComponent {
    
    private int goal;
    private int total;
    private int width;
    private int width1;
    private int width2;
    
    /**
     * Creates new form StatusBar
     */
    public StatusBarPositive(int g, int t) {
        
        setPreferredSize(new Dimension(280, 50));
        setBackground(Color.GREEN);
        
        goal = g;
        total = t;
        width = 280;
        width1 = 0;
        width2 = 0;
        System.out.println("status bar here");
        getWidthPositive();
    }
    
    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        System.out.println("pref size");
    }
    
    @Override
    public void paintComponent(Graphics g) {
       
        
        super.paintComponent(g);
        Graphics2D box = (Graphics2D) g;
        
        
          System.out.println("Positive");   
        
            
            
        // green
        
        box.setColor(new Color(58, 222, 69));
        
        box.fillRect(0, 0, width1, 45);
            
        
        // light grey
        
        box.setColor(new Color(212, 212, 212));
        
        box.fillRect(width1, 0, width1 + width2, 45);
        
        
    }
    
    private void getWidthPositive() {
        System.out.println("width set");
        float x = width * total / goal;
        width1 = (int) x;
        width2 = width - width1;
    }
    
    
}
