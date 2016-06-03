
package myfitdayapp;


import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * class for creating the pie chart representation date specific macros
 * 
 * @author olgachristensen
 */
public class PieChart {
    
    public ChartPanel panel;
    private JFreeChart chart;
    private PiePlot plot;
    private DefaultPieDataset chartData;
    
    private int f;
    private int c;
    private int p;
    
    // constructor for empty chart when macros==null
    public PieChart() {
        // default values for macros
        f = 30;
        c = 30;
        p = 30;
        
        initialise();
        
        plot.setSectionPaint(0, Color.gray);
        plot.setSectionPaint(1, Color.lightGray);
        plot.setSectionPaint(2, Color.white);
           
    }
    
    // constructor for chart when macros!=null
    public PieChart(int fat, int carbs, int protein) {
        
        f = fat;
        c = carbs;
        p = protein;
        
        initialise();
        
    }
    
    private void initialise() {
        // data for the chart
        chartData = new DefaultPieDataset();
        chartData.setValue("fat", new Integer(f));
        chartData.setValue("carbs", new Integer(c));
        chartData.setValue("protein", new Integer(p));
        
        // no title, data, legend, tooltips, url
        chart = ChartFactory.createPieChart(null, chartData, false, false, false);
        
        // same background as the panel in MainFrame
        chart.setBackgroundPaint(new Color(51, 51, 51));
        
        // create chart plot 
        plot = (PiePlot) chart.getPlot();
        
        
        plot.setInteriorGap(0.01);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(null);
        //plot.setForegroundAlpha(0.8f);
        
        plot.setSectionPaint(0, new Color(135, 198, 197));
        plot.setSectionPaint(1, new Color(177, 192, 41));
        plot.setSectionPaint(2, new Color(222, 0, 33));
        
        plot.setShadowXOffset(0);
        plot.setShadowYOffset(0);
        
        plot.setLabelGenerator(null);
        //plot.setSimpleLabels(true);
    
        
        panel = new ChartPanel(chart);
        panel.setMinimumDrawWidth(0);
        panel.setMinimumDrawHeight(0);
        
        panel.setVisible(true);
        panel.setSize(195, 195);
        
        //panel.setBackground(Color.white);
        //chart.setBackgroundPaint(new Color(0,0,0,0));
    }
    
}
