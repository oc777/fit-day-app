/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author olgachristensen
 */
public class Boxes extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLUE);
    g2d.fillRect(10, 15, 90, 60);


    g2d.setColor(Color.PINK);
    g2d.fillRect(100, 15, 90, 60);

  }

  public static void main(String[] args) {
    Boxes rects = new Boxes();
    JFrame frame = new JFrame("Rectangles");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(rects);
    frame.setSize(360, 300);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
