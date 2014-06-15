import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class CinemaContentPanel extends JPanel
{
	//this class is used to make all panels look the same :)
	public CinemaContentPanel() 
	{
		this.setVisible(true);
		this.setBackground(new Color(0.5f,0.9f,0.5f));
		this.setSize(800, 400);
		this.setLayout(null);
	}
}
