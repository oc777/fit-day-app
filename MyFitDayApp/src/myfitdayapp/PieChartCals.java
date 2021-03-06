
package myfitdayapp;


import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * class for creating the pie chart representation of date specific macros
 * (fat, carbs, protein)
 * 
 * @author olgachristensen
 */
public class PieChartCals {
    
    public ChartPanel panel;
    public JFreeChart chart;
    private PiePlot plot;
    private DefaultPieDataset chartData;
    
    private int food;
    private int sport;
    
    
    // constructor for empty chart 
    public PieChartCals() {
        // default values for chart
        food = 70;
        sport = 30;
        
        
        initialise();
        
        plot.setSectionPaint(0, Color.gray);
        plot.setSectionPaint(1, Color.lightGray);
           
    }
    
    // constructor for sport & food != null
    public PieChartCals(int f, int s) {
        
        food = f;
        sport = s;
        
        initialise();
        
    }
    
    // draw chart and decorate
    private void initialise() {
        // data for the chart
        chartData = new DefaultPieDataset();
        chartData.setValue("food", new Integer(food));
        chartData.setValue("sport", new Integer(sport));
        
        // no title, data, legend, tooltips, url
        chart = ChartFactory.createPieChart(null, chartData, false, false, false);
        
        // same background as the panel in MainFrame
        chart.setBackgroundPaint(new Color(51, 51, 51));
        
        // create chart plot 
        plot = (PiePlot) chart.getPlot();
        
        
        plot.setInteriorGap(0.01);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(null);
        
        plot.setSectionPaint(0, new Color(177, 192, 41));
        plot.setSectionPaint(1, new Color(222, 0, 33));
        
        plot.setShadowXOffset(0);
        plot.setShadowYOffset(0);
        
        plot.setLabelGenerator(null);
    
        
        panel = new ChartPanel(chart);
        panel.setMinimumDrawWidth(0);
        panel.setMinimumDrawHeight(0);
        
        panel.setVisible(true);
        panel.setSize(195, 195);
        
    }
    
    
}
