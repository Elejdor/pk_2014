import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
import java.sql.Date;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyCinemaCustomerView extends CinemaContentPanel
{
	private MyCinemaController myCinemaController;
	
	private JPanel tabtickets;
	private JPanel tabMovies;
	private JPanel tabShows;
	private JPanel tabTickets;
	private JPanel tabFinancials;
	
	private class Seat
	{
		public JButton button;
		public boolean booked = false;
	}
	
	//tickets tab
	public JScrollPane shows_scrollList;
	public JList<String> shows_showsList;
	public ArrayList<ShowsRow> showsList;
	public ArrayList<Seat> seats = new ArrayList<Seat>();
	public ArrayList<TicketsRow> tickets = new ArrayList<TicketsRow>();
	
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
		tabtickets.setLayout(null);
		tabtickets.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabtickets);
		
		this.shows_showsList = new JList<String>();
		this.shows_showsList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.shows_scrollList = new JScrollPane();
		this.shows_scrollList.setBounds(50, 50, 350, 270);
		this.shows_scrollList.setVisible(true);
		this.shows_scrollList.setViewportView(shows_showsList);
		tabtickets.add(shows_scrollList);
		this.showsList = this.myCinemaController.GetShowsList();
		this.shows_showsList.setListData(MyCinemaController.ShowsRowListToArray(this.showsList));
		createSeats(7, 5, 420, 50, 50, 30, 2);
		
		ListSelectionListener actionIndexChanged = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(shows_showsList.getSelectedIndex() != -1)
				{
					tickets = myCinemaController.GetTicketsList(showsList.get(shows_showsList.getSelectedIndex()).show_id);
					
					fillSeatsForShow();
					System.out.println("Filling");
				} else{
					System.out.println("Wut");
				}
			}
		};
		this.shows_showsList.addListSelectionListener(actionIndexChanged);	
		
		
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
	
	private void fillSeatsForShow()
	{

		for (int i = 0; i < seats.size(); i++) {
			seats.get(i).button.setBackground(Color.green);

			for (int j = 0; j < tickets.size(); j++) {
				System.out.println(tickets.get(j).ticket_show_id);
				if (tickets.get(j).ticket_seat_number == i)
				{
					//TODO if (tickets.get(j).user_id == loggedin) Color = blue;
					seats.get(i).button.setBackground(Color.red);
				}

			}
		}
	}
	
	private void createSeats(int rows, int cols, int startPosX, int startPosY, int width, int height, int margins)
	{
		
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Seat tmpSeat = new Seat();
				JButton tmpBtn = new JButton(Integer.toString(c+cols*r));
				tmpBtn.setBounds(startPosX + c * (width+margins), startPosY + r* (height + margins), width, height);
				tabtickets.add(tmpBtn);
				tmpSeat.button = tmpBtn;
				seats.add(tmpSeat);
			}
				
		}
	}
}