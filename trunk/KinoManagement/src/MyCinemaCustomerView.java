import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MyCinemaCustomerView extends CinemaContentPanel
{
	private MyCinemaController myCinemaController;
	
	private JPanel tabtickets;
	private JPanel tabMovies;
	private JPanel tabShows;
	private JPanel tabTickets;
	private JPanel tabFinancials;
	
	public MyCinemaCustomerView(MyCinemaController myCinemaController)
	{
		this.myCinemaController = myCinemaController;
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		Rectangle tabRectangle = this.getBounds();
		tabRectangle.height -= 4;
		tabRectangle.width -= 4;
		tabRectangle.y += 2;
		tabRectangle.x += 2;
		jTabbedPane.setBounds(tabRectangle);

		initializeTabTickets(jTabbedPane);
		initializeTabShows(jTabbedPane);
		initializeTabFinancials(jTabbedPane);
		
		this.add(jTabbedPane);
	}
	private void initializeTabTickets(JTabbedPane jTabbedPane)
	{
		tabtickets = new JPanel();
		tabtickets.setName("Tickets");
		tabtickets.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabtickets);
	}
	private void initializeTabShows(JTabbedPane jTabbedPane)
	{
		tabShows = new JPanel();
		tabShows.setName("Shows");
		tabShows.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabShows);
	}
	private void initializeTabFinancials(JTabbedPane jTabbedPane)
	{
		tabFinancials = new JPanel();
		tabFinancials.setName("Financials");
		tabFinancials.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabFinancials);
	}
}