import java.awt.Color;
import javax.swing.*;
import org.jfree.*;

public class Program extends JApplet
{
	JPanel contentPanel;
	
	public void init()
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable() 
			{
				public void run() 
				{	
					CreateApplet();
				}
			});
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void CreateApplet()
	{
		this.setSize(500, 500);
		this.setBackground(Color.cyan);
		this.contentPanel = new JPanel();
		this.setContentPane(contentPanel);
		this.contentPanel.setSize(500,500);
		this.contentPanel.setBackground(Color.green);
		
		double[] xs = new double[5];
		double[] ys = new double[5];
		xs[0] = 0;
		xs[1] = 1;
		xs[2] = 2;
		xs[3] = 3;
		xs[4] = 4;
		
		ys[0] = 0;
		ys[1] = 1;
		ys[2] = 2;
		ys[3] = 3;
		ys[4] = 4;
		JPanel chartPanel = new LineChart(xs,"Month numbers", ys,"","linia",400,400, Color.green.brighter());
		contentPanel.add(chartPanel);
	}
}
