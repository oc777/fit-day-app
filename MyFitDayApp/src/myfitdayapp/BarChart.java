
package myfitdayapp;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author olgachristensen
 */
public class BarChart {
    
    private String[] date;
    private int[] goal;
    private int[] total;
    
    public ChartPanel panel;
    private JFreeChart chart;
    private CategoryDataset data;
    private BarRenderer renderer;
    
    
    public BarChart() {
        date = new String[]{"yyyy-mm-dd"};
        goal = new int[] {1500};
        total = new int[] {1000};
        
        initialise();
        renderer.setSeriesPaint(0, Color.gray);
        renderer.setSeriesPaint(1, Color.darkGray);
    }
    
    public BarChart(String[] dates, int[] goals, int[] totals) {
        date = dates;
        goal = goals;
        total = totals;
        
        initialise();
    }
    
    private void initialise() {
        data = getData();
        createChart();
        
        panel = new ChartPanel(chart);
        
        panel.setMinimumDrawWidth(0);
        panel.setMinimumDrawHeight(0);
        
        panel.setVisible(true);
        panel.setSize(880, 250);
        
    }
    
    private DefaultCategoryDataset getData() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
       
        for (int i = 0; i < date.length; i++) {
            ds.addValue(total[i], "Total", date[i]);
        }
        
        for (int i = 0; i < date.length; i++) {
            ds.addValue(goal[i], "Goal", date[i]);
        }
        
        
        return ds;
    }
    
    
    private void createChart() {
        chart = ChartFactory.createBarChart(null, null, null, data);
        chart.removeLegend();
        // same background as the panel in MainFrame
        chart.setBackgroundPaint(new Color(51, 51, 51));
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        //plot.getDomainAxis().setVisible(false);
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

        renderer = (BarRenderer) plot.getRenderer();
        
        renderer.setDrawBarOutline(false);
        renderer.setSeriesPaint(0, new Color(119, 170, 170));
        renderer.setSeriesPaint(1, new Color(220, 220, 125));
        renderer.setMaximumBarWidth(0.1);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        
        
        
        // category axis, X
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelPaint(Color.WHITE);
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 12));
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 3.0)
        );
        
        // value axis, Y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setVisible(false);
        //rangeAxis.setTickLabelPaint(Color.WHITE);
        //rangeAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 12));
        rangeAxis.setLowerMargin(0.1);
        rangeAxis.setUpperMargin(0.25);
        
    }
}
