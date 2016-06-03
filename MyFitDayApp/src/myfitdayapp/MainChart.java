/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author olgachristensen
 */
public class MainChart {
    
    public ChartPanel panel;
    private JFreeChart chart;
    //private CategoryDataset data1;
    private CategoryDataset data2;
    private CategoryPlot plot1;
    private XYPlot plot;
    
    private String[] date;
    private int[] goal;
    private int[] total;
    
    private int l;
    
    
    public MainChart() {
        
    }
    
    public MainChart(String[] dates, int[] goals, int[] totals) {
        date = dates;
        goal = goals;
        total = totals;
        l = date.length;
        
        System.out.println(date.length);
        System.out.println(goal.length);
        System.out.println(total.length);
        
        initialise();
    }
    
    private void initialise() {
        //data1 = getData1();
        data2 = getData2();
        
        chart = createChart(data2);
        panel = new ChartPanel(chart);
        
        panel.setMinimumDrawWidth(0);
        panel.setMinimumDrawHeight(0);
        
        panel.setVisible(true);
        panel.setSize(600, 250);
                
                
    }
    
    
    // XY series
    private CategoryDataset getData2() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        
        //String goalChart = "Goal";
        //String totalChart = "Total";
        
        for (int i = 0; i < l; i++) {
            ds.addValue(goal[i], "goal", date[i]);
        }
        
        for (int i = 0; i < l; i++) {
            ds.addValue(total[i], "total", date[i]);
        }
        
        
        return ds;
    }
    
    private JFreeChart createChart(CategoryDataset ds) {
        
        chart = ChartFactory.createLineChart(
            null,                      // chart title
            null,                    // domain axis label
            null,                   // range axis label
            ds,                        // data
            PlotOrientation.VERTICAL,  // orientation
            false,                      // include legend
            false,                      // tooltips
            false                      // urls
        );
        
        chart.setBackgroundPaint(Color.white);
        
        
        plot1 = (CategoryPlot) chart.getPlot();
        plot1.setBackgroundPaint(Color.lightGray);
        plot1.setRangeGridlinePaint(Color.white);
        
        
        
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(false);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        //rangeAxis.setAutoRangeIncludesZero(false);
        
        //LineAndShapeRenderer lr = (LineAndShapeRenderer) plot.getRenderer();
        //StandardXYItemRenderer renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES);
        //renderer.setShapesFilled(true);
        //plot.setRenderer((CategoryItemRenderer) renderer);
        
        return chart;
        
    }
    
    
    // bar chart
    private CategoryDataset getData1() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        
        String food = "Meals";
        String sport = "Sport";
        
        
        
        return ds;
    }
    
    
}



/*
        chart = ChartFactory.createBarChart(
                null,           // title
                "category",     // domain axis lable
                "value",        // range axis lable
                data1,          // data
                PlotOrientation.VERTICAL,   // vertical bars
                false, false, false         // legend, tooltip, URL
        );
        
        
        
        
        // same background as the panel in Statistics
        //chart.setBackgroundPaint(new Color(51, 51, 51));
        //chart.setBorderPaint(Color.WHITE);
        
        // create chart plot 
        //plot = chart.getCategoryPlot();
        //plot.setBackgroundPaint(Color.LIGHT_GRAY);
        //plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        */
