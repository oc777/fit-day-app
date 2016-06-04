
package myfitdayapp;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Bar Chart in Statistics
 * shows the difference between the goal set for the day and actual calorie intake
 * 
 * @author olgachristensen
 */
public class BarChart1 {
    
    private String[] date;
    private int[] goal;
    private int[] total;
    
    public ChartPanel panel;
    private JFreeChart chart;
    private CategoryPlot plot;
    private DefaultCategoryDataset data1;
    private DefaultCategoryDataset data2;
    private CategoryItemRenderer r1;
    private CategoryItemRenderer r2;
    
    
    // constructor for empty chart (no data available)
    public BarChart1() {
        date = new String[]{"yyyy-mm-dd"};
        goal = new int[] {1500};
        total = new int[] {1000};
        
        initialise();
        r1.setSeriesPaint(0, Color.gray);
    }
    
    // constructor for bar chart with data
    public BarChart1(String[] dates, int[] goals, int[] totals) {
        date = dates;
        goal = goals;
        total = totals;
        
        initialise();
    }
    
    // create chart
    private void initialise() {
        data1 = getData1();
        data2 = getData2();
        
        createChart();
        
        panel = new ChartPanel(chart);
        
        panel.setMinimumDrawWidth(0);
        panel.setMinimumDrawHeight(0);
        
        panel.setVisible(true);
        panel.setSize(880, 250);
        
    }
    
    // create data set (bars)
    private DefaultCategoryDataset getData1() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
       
        // add totals
        for (int i = 0; i < date.length; i++) {
            ds.addValue(total[i], "Total", date[i]);
        }
        
        return ds;
    }
    
    // create data set (lines)
    private DefaultCategoryDataset getData2() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
       
        // add goal
        for (int i = 0; i < date.length; i++) {
            ds.addValue(goal[i], "Goal", date[i]);
        }
        
        
        return ds;
    }
    
    
    // draw and decorate the chart
    private void createChart() {
        
        //CategoryPlot plot = chart.getCategoryPlot();
        plot = new CategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        plot.setOrientation(PlotOrientation.VERTICAL);
        // show bars without gradient
        //((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

        r1 = new BarRenderer();
        r1.setSeriesPaint(0, new Color(119, 170, 170));
        r1.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        r1.setBaseItemLabelsVisible(true);
        
        
        r2 = new LineAndShapeRenderer();
        
        plot.setDataset(data1);
        plot.setRenderer(r1);
        
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        ((BarRenderer) plot.getRenderer()).setDefaultShadowsVisible(false);
        ((BarRenderer) plot.getRenderer()).setMaximumBarWidth(0.05);
        
        plot.setDataset(1, data2);
        plot.setRenderer(1, r2);
        
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        // category axis, X
        //CategoryAxis domainAxis = plot.getDomainAxis();
        CategoryAxis domainAxis = new CategoryAxis();
        domainAxis.setTickLabelPaint(Color.WHITE);
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 12));
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 3.0)
        );
        
        // value axis, Y
        //NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setVisible(false);
        rangeAxis.setLowerMargin(0.1);
        rangeAxis.setUpperMargin(0.25);
        
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        
        
        chart = new JFreeChart(plot);
        // don't show the legend
        chart.removeLegend();
        // same background as the panel in MainFrame
        chart.setBackgroundPaint(new Color(51, 51, 51));
        
    }
    
    private void trim(ArrayList<String> d, ArrayList<Integer> g, ArrayList<Integer> t) {
        // remove zero records
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) == 0) {
                t.remove(i);
                g.remove(i);
                d.remove(i);
            }
        }
        
        // ArrayList to Array
        
        date = d.toArray(new String[d.size()]);
        
        goal = new int[g.size()];
        for (int i = 0; i < goal.length; i++)
            goal[i] = g.get(i);
        
        total = new int[t.size()];
        for (int i = 0; i < total.length; i++)
            total[i] = t.get(i);
        
        
        
    }
}
