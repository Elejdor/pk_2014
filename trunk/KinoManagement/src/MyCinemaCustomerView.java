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
	private JPanel tabProfile;
	
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
	private JComboBox<String> cbMovies;
	private ArrayList<MoviesRow> MoviesList = new ArrayList<MoviesRow>();
	
	//profile tab
	private ArrayList<JTextField> inputForm = new ArrayList<JTextField>();
	UsersRow currentlyLogged;
	
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
		initializeTabProfile(jTabbedPane);
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
		
		
		//movies combo box
		showsList = myCinemaController.GetShowsList();
		ArrayList<MoviesRow> tmpMovies = myCinemaController.GetMoviesList();
		
		cbMovies = new JComboBox<String>();
		for (int i = 0; i < tmpMovies.size(); i++) {
			for (int j = 0; j < showsList.size(); j++)
			{
				if (tmpMovies.get(i).movie_id == showsList.get(j).show_movie_id)
				{
					MoviesList.add(tmpMovies.get(i));
					cbMovies.addItem(tmpMovies.get(i).movie_title);
					break;
				}
			}
		}
		this.cbMovies.setBounds(30, 10, 200, 30);
		this.cbMovies.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showsList = myCinemaController.GetShowsList(MoviesList.get(cbMovies.getSelectedIndex()).movie_id);		
				shows_showsList.setListData(MyCinemaController.ShowsRowListToArray(showsList));
				clearSeats();
			}
		});
		this.tabtickets.add(cbMovies);
		
		//Shows list
		this.shows_showsList = new JList<String>();
		this.shows_showsList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.shows_scrollList = new JScrollPane();
		this.shows_scrollList.setBounds(50, 50, 350, 270);
		this.shows_scrollList.setVisible(true);
		this.shows_scrollList.setViewportView(shows_showsList);
		
		this.tabtickets.add(shows_scrollList);
		
		showsList = myCinemaController.GetShowsList(MoviesList.get(cbMovies.getSelectedIndex()).movie_id);
		this.shows_showsList.setListData(MyCinemaController.ShowsRowListToArray(this.showsList));
		
		createSeats(7, 5, 420, 50, 50, 30, 2);
		
		ListSelectionListener actionIndexChanged = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(shows_showsList.getSelectedIndex() != -1)
				{					
					fillSeatsForShow();
				} else{

				}
			}
		};
		this.shows_showsList.addListSelectionListener(actionIndexChanged);	
		
		
	}
	
	private void initializeTabProfile(JTabbedPane jTabbedPane)
	{
		tabProfile = new JPanel();
		tabProfile.setName("My profile");
		tabProfile.setLayout(null);
		tabProfile.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabProfile);		
		
		
		int tfWidth = 100, tfHeight = 25;
		currentlyLogged = myCinemaController.GetUser(myCinemaController.logged_user_id);
		String[][] tfValues = new String[][] {
				{"Login", currentlyLogged.user_login}, 
				{"Password", currentlyLogged.user_pass} ,
				{"Name", currentlyLogged.user_name}, 
				{"Surname", currentlyLogged.user_surname}, 
				{"age", Integer.toString(currentlyLogged.user_age)}};
		
		for (int i = 0; i < tfValues.length; i++)
		{
			CreateLabel(tfValues[i][0], tabProfile, 50, 50+i*(tfHeight + 5), tfWidth, tfHeight);
			inputForm.add(CreateTextField(tfValues[i][1], tabProfile, 50+100, 50+i*(tfHeight + 5), tfWidth, tfHeight));			
		}
		
		JButton btnSubmit = new JButton();
		btnSubmit.setText("Submit");
		btnSubmit.setBounds(50, (tfValues.length+1)*(tfHeight+5)+50, 100, 25);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				UsersRow updatingUser = new UsersRow();
				updatingUser.user_id = myCinemaController.logged_user_id;
				updatingUser.user_login = inputForm.get(0).getText();
				updatingUser.user_pass = inputForm.get(1).getText();
				updatingUser.user_type = currentlyLogged.user_type;
				updatingUser.user_name = inputForm.get(2).getText();
				updatingUser.user_surname = inputForm.get(3).getText();
				updatingUser.user_age = Integer.parseInt(inputForm.get(4).getText());
				
				myCinemaController.UpdateUser(updatingUser);
			}
		});
		tabProfile.add(btnSubmit);
	}
	
	private JTextField CreateTextField(String text, JPanel panel, int x, int y, int width, int height)
	{
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.setText(text);
		panel.add(textField);
		return textField;
	}
	
	private JLabel CreateLabel(String text, JPanel panel, int x, int y, int width, int height)
	{
		JLabel label = new JLabel();
		label.setBounds(x, y, width, height);
		label.setText(text);
		panel.add(label);
		return label;
	}
	
	private void initializeTabFinancials(JTabbedPane jTabbedPane)
	{
		tabFinancials = new JPanel();
		tabFinancials.setName("Financials");
		tabFinancials.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabFinancials);
	}
	
	private void clearSeats()
	{
		for (int i = 0; i < seats.size(); i++) {
			seats.get(i).button.setBackground(Color.gray);
			seats.get(i).button.setEnabled(false);
		}
	}
	
	private void fillSeatsForShow()
	{		
		tickets = myCinemaController.GetTicketsList(showsList.get(shows_showsList.getSelectedIndex()).show_id);
		for (int i = 0; i < seats.size(); i++) 
		{
			seats.get(i).button.setBackground(Color.green);
			seats.get(i).button.setEnabled(true);
			
			ClearActionListeners(seats.get(i).button);
			
			seats.get(i).button.addActionListener(GetListenerForRegular());
			
			for (int j = 0; j < tickets.size(); j++) 
			{
				if (tickets.get(j).ticket_seat_number == i)
				{
					ClearActionListeners(seats.get(i).button);
					
					if (myCinemaController.logged_user_id == tickets.get(j).ticket_user_id)
					{
						if (tickets.get(j).ticket_state.equals("BOUGHT"))
						{
							seats.get(i).button.setBackground(Color.yellow);	
							seats.get(i).button.addActionListener(GetListenerForBought());
						}
						else
						{
							seats.get(i).button.setBackground(Color.blue);
							seats.get(i).button.addActionListener(GetListenerForReserved());
						}
					}
					else
					{
						seats.get(i).button.setBackground(Color.red);
						seats.get(i).button.setEnabled(false);
					}
				}

			}
		}
	}
	
	private void ClearActionListeners(JButton button)
	{
		ActionListener[] als = button.getActionListeners();
		for (int a = 0; a < als.length; a++) {
			button.removeActionListener(als[a]);
		}
	}
	
	private ActionListener GetListenerForReserved()
	{
		ActionListener action = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				Object[] possibleValues = { "Buy", "Cancel reservation"};
				Object selectedValue = JOptionPane.showInputDialog(null,
					"Choose one", "Input",
					JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
				JButton btn = (JButton)e.getSource();
				
				int seatNumber = Integer.parseInt(btn.getText());
				
				if (selectedValue != null)
				{
					TicketsRow tmpTicket = new TicketsRow();
					for (int i = 0; i < tickets.size(); i++) {
						if (tickets.get(i).ticket_seat_number == seatNumber)
						{
							tmpTicket = tickets.get(i);
							break;
						}
					}					
					if (selectedValue.equals(possibleValues[0]))
					{
						btn.setBackground(Color.yellow);
						myCinemaController.UpdateTicket(tmpTicket.ticket_id, "BOUGHT");
					}
					else if (selectedValue.equals(possibleValues[1]))
					{
						btn.setBackground(Color.green);
						myCinemaController.DeleteTicket(tmpTicket.ticket_id);						
					}
				}
				fillSeatsForShow();
			}
		};
		return action;
	}
	
	private ActionListener GetListenerForBought()
	{
		ActionListener action = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				Object[] possibleValues = { "Return ticket"};
				Object selectedValue = JOptionPane.showInputDialog(null,
					"Choose one", "Input",
					JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
				JButton btn = (JButton)e.getSource();
				
				int seatNumber = Integer.parseInt(btn.getText());
				
				//int showId = showsList.get(shows_showsList.getSelectedIndex()).show_id;
				
				if (selectedValue != null)
				{
					TicketsRow tmpTicket = new TicketsRow();
					for (int i = 0; i < tickets.size(); i++) {
						if (tickets.get(i).ticket_seat_number == seatNumber)
						{
							tmpTicket = tickets.get(i);
							break;
						}
					}
					
					if (selectedValue.equals(possibleValues[0]))
					{
						btn.setBackground(Color.green);
						System.out.println("Bought ticket removed " + Integer.toString(tmpTicket.ticket_id));
						myCinemaController.DeleteTicket(tmpTicket.ticket_id);
					}
					
				}
				fillSeatsForShow();
			};
		};
		return action;
	}
	
	private ActionListener GetListenerForRegular()
	{
		ActionListener action = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();				
				int seatNumber = Integer.parseInt(btn.getText());				
				int showId = showsList.get(shows_showsList.getSelectedIndex()).show_id;
				
				TicketsRow ticketBuilder = new TicketsRow();
				ticketBuilder.ticket_seat_number = seatNumber;
				ticketBuilder.ticket_show_id = showId;
				ticketBuilder.ticket_state = "RESERVED";
				ticketBuilder.ticket_user_id = myCinemaController.logged_user_id;
				
				myCinemaController.AddTicket(ticketBuilder);
				
				fillSeatsForShow();
			};
		};
		return action;
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
				tmpBtn.setEnabled(false);
				tmpBtn.setBackground(Color.gray);
				tabtickets.add(tmpBtn);
				tmpSeat.button = tmpBtn;
				seats.add(tmpSeat);
			}
				
		}
	}
}