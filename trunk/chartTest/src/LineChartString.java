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

public class LineChartString extends JPanel
{
	XYSeriesCollection dataCollection;
	XYDataset dataSet;
	JFreeChart chart;
	ChartPanel chartPanel;
	public LineChartString(double[] X, double[] Y, String title, int width, int height)
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
		            "X",                      // x axis label
		            "Y",                      // y axis label
		            dataSet,                  // data
		            PlotOrientation.VERTICAL,
		            true,                     // include legend
		            false,                     // tooltips
		            false                     // urls
		        );
		
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(width, height));
		this.add(chartPanel);
		
		//((CategoryPlot) chartPanel.getChart().getPlot()).getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	}
}