import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends JPanel
{
	XYSeriesCollection dataCollection;
	XYDataset dataSet;
	JFreeChart chart;
	ChartPanel chartPanel;
	public LineChart(double[] X,String xString, double[] Y,String yString, String title, int width, int height, Color color)
	{
		XYSeries dataSeries = new XYSeries(title);
		for(int i = 0 ; i < X.length;i++)
		{
			dataSeries.add(X[i],Y[i]);
		}
		XYSeriesCollection dataCollection = new XYSeriesCollection();
		dataCollection.addSeries(dataSeries);
		this.dataSet = dataCollection;
		
		chart = ChartFactory.createXYLineChart(
		            title,      // chart title
		            xString,                      // x axis label
		            yString,                      // y axis label
		            dataSet,                  // data
		            PlotOrientation.VERTICAL,
		            false,                     // include legend
		            false,                     // tooltips
		            false                     // urls
		        );
		chart.setBackgroundPaint(color); 
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(width, height));
		this.add(chartPanel);
	}
}