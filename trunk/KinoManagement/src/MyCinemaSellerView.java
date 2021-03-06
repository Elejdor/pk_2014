import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

//TODO append update to every list in tabs to selected tab index change

public class MyCinemaSellerView extends CinemaContentPanel
{
	public static MyCinemaSellerView sellerView = null;
	
	public MyCinemaController myCinemaController;
	
	//users tab
	public JScrollPane users_scrollList;
	public JList<String> users_userList;
	public ArrayList<UsersRow> userList;
	public JTextField users_login;
	public JTextField users_pass;
	public JComboBox users_type;
	public JTextField users_name;
	public JTextField users_surname;
	public JTextField users_age;
	public JButton users_buttonUpdate;
	public JButton users_buttonDeselect;
	public JButton users_buttonAdd;
	public JButton users_buttonDelete;
	
	//movies tab
	public JScrollPane movies_scrollList;
	public JList<String> movies_movieList;
	public ArrayList<MoviesRow> movieList;
	public JTextField movies_title;
	public JTextField movies_lenth;
	public JTextField movies_min_age;
	public JTextField movies_price;
	public JComboBox movies_genre;
	public JTextField movies_licence_price;
	public JTextField movies_licence_payment_day;
	public JButton movies_buttonUpdate;
	public JButton movies_buttonDeselect;
	public JButton movies_buttonAdd;
	public JButton movies_buttonDelete;
	
	//shows tab
	public JScrollPane shows_scrollList;
	public JList<String> shows_showList;
	public ArrayList<ShowsRow> showList;
	public JScrollPane shows_movie_scrollList;
	public JList<String> shows_movieListJList;
	public ArrayList<MoviesRow> shows_movieListArray;
	public JTextField shows_date;
	public JButton shows_buttonDelete;
	public JButton shows_buttonDeselectMovie;
	public JButton shows_buttonDeselectShow;
	public JButton shows_buttonUpdate;
	public JButton shows_buttonAdd;
	public JButton shows_buttonToXml;
	public JTextField shows_fileName;
	
	//tickets tab
	public JScrollPane tickets_shows_scrollList;
	public JList<String> tickets_shows_showList;
	public ArrayList<ShowsRow> tickets_showList;
	public JScrollPane tickets_shows_movie_scrollList;
	public JList<String> tickets_shows_movieListJList;
	public ArrayList<MoviesRow> tickets_shows_movieListArray;
	public JScrollPane tickets_scrollList;
	public JList<String> tickets_ticketList;
	public ArrayList<TicketsRow> ticketList;
	public JButton tickets_buttonDeselectMovies;
	public JButton tickets_buttonDeselectShows;
	public JButton tickets_buttonDeselectTickets;
	public JTextField tickets_user_id;
	public JTextField tickets_seat_number;
	public JComboBox tickets_state;
	public JButton tickets_buttonUpdate;
	public JButton tickets_buttonDelete;
	public JButton tickets_buttonAdd;
	
	//financials tab
	public JScrollPane financials_scrollList;
	public JList<String> financials_financialList;
	public ArrayList<FinancialsRow> financialList;
	public JTextField financials_value;
	public JTextField financials_date;
	public JComboBox<String> financials_type;
	public JTextField financials_description;
	public JButton financials_buttonDeselect;
	public JButton financials_buttonDelete;
	public JButton financials_buttonUpdate;
	public JButton financials_buttonAdd;
	
	//chart tab
	public JTextField chart_year;
	public JButton chart_draw;
	public JPanel chart_panel;
	
	//tabs
	public JPanel tabUsers;
	public JPanel tabMovies;
	public JPanel tabShows;
	public JPanel tabTickets;
	public JPanel tabFinancials;
	public JPanel tabChart;
	
	//initializing
	public MyCinemaSellerView(MyCinemaController myCinemaController)
	{
		sellerView = this;
		
		this.myCinemaController = myCinemaController;
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		Rectangle tabRectangle = this.getBounds();
		tabRectangle.height -= 4;
		tabRectangle.width -= 4;
		tabRectangle.y += 2;
		tabRectangle.x += 2;
		jTabbedPane.setBounds(tabRectangle);

		initializeTabUsers(jTabbedPane);
		initializeTabMovies(jTabbedPane);
		initializeTabShows(jTabbedPane);
		initializeTabTickets(jTabbedPane);
		initializeTabFinancials(jTabbedPane);
		initializeTabChart(jTabbedPane);
		
		this.add(jTabbedPane);
	}
	private void initializeTabUsers(JTabbedPane jTabbedPane)
	{
		tabUsers = new JPanel();
		tabUsers.setName("Users");
		tabUsers.setLayout(null);
		tabUsers.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabUsers);

		this.users_userList = new JList<String>();
		this.users_userList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.users_scrollList = new JScrollPane();
		this.users_scrollList.setBounds(50, 50, 350, 270);
		this.users_scrollList.setVisible(true);
		this.users_scrollList.setViewportView(users_userList);
		tabUsers.add(users_scrollList);
		this.userList = this.myCinemaController.GetUsersList();
		this.users_userList.setListData(MyCinemaController.UsersRowListToArray(this.userList));
		ListSelectionListener actionIndexChanged = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{ 
				if(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex() != -1)
				{
					MyCinemaSellerView.sellerView.users_login.setText(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_login);
					MyCinemaSellerView.sellerView.users_pass.setText(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_pass);
					
					if(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_type.equals("CUSTOMER"))
						MyCinemaSellerView.sellerView.users_type.setSelectedIndex(0);
					else
						MyCinemaSellerView.sellerView.users_type.setSelectedIndex(1);
					
					MyCinemaSellerView.sellerView.users_name.setText(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_name);
					MyCinemaSellerView.sellerView.users_surname.setText(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_surname);
					MyCinemaSellerView.sellerView.users_age.setText(String.valueOf(MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_age));
				}else{
					MyCinemaSellerView.sellerView.users_login.setText("");
					MyCinemaSellerView.sellerView.users_pass.setText("");
					MyCinemaSellerView.sellerView.users_type.setSelectedIndex(0);					
					MyCinemaSellerView.sellerView.users_name.setText("");
					MyCinemaSellerView.sellerView.users_surname.setText("");
					MyCinemaSellerView.sellerView.users_age.setText("");
				}
			}
		};
		this.users_userList.addListSelectionListener(actionIndexChanged);
		
		JLabel users_login_label = new JLabel("Login:");
		users_login_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_login_label.setBounds(410, 50,100,20);
		users_login_label.setVisible(true);
		tabUsers.add(users_login_label);
		this.users_login = new JTextField();
		this.users_login.setBounds(510, 50, 100, 20);
		tabUsers.add(users_login);
		
		JLabel users_pass_label = new JLabel("Password:");
		users_pass_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_pass_label.setBounds(410, 100,100,20);
		users_pass_label.setVisible(true);
		tabUsers.add(users_pass_label);
		this.users_pass = new JTextField();
		this.users_pass.setBounds(510, 100, 100, 20);
		tabUsers.add(users_pass);
		
		JLabel users_type_label = new JLabel("Type:");
		users_type_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_type_label.setBounds(410, 150,100,20);
		users_type_label.setVisible(true);
		tabUsers.add(users_type_label);
		users_type = new JComboBox();
		users_type.setEditable(false);
		users_type.setBounds(510, 150, 100, 20);
		users_type.addItem("CUSTOMER");
		users_type.addItem("SELLER");
		tabUsers.add(users_type);
		
		JLabel users_name_label = new JLabel("Name:");
		users_name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_name_label.setBounds(410, 200,100,20);
		users_name_label.setVisible(true);
		tabUsers.add(users_name_label);
		this.users_name = new JTextField();
		this.users_name.setBounds(510, 200, 100, 20);
		tabUsers.add(users_name);
		
		JLabel users_surname_label = new JLabel("Surname:");
		users_surname_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_surname_label.setBounds(410, 250,100,20);
		users_surname_label.setVisible(true);
		tabUsers.add(users_surname_label);
		this.users_surname = new JTextField();
		this.users_surname.setBounds(510, 250, 100, 20);
		tabUsers.add(users_surname);
		
		JLabel users_age_label = new JLabel("Age:");
		users_age_label.setHorizontalAlignment(SwingConstants.RIGHT);
		users_age_label.setBounds(410, 300,100,20);
		users_age_label.setVisible(true);
		tabUsers.add(users_age_label);
		this.users_age = new JTextField();
		this.users_age.setBounds(510, 300, 100, 20);
		tabUsers.add(users_age);
		
		users_buttonUpdate = new JButton("Update");
		users_buttonUpdate.setBounds(620, 50, 100, 40);
		tabUsers.add(users_buttonUpdate);
		ActionListener actionUpdate = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex() != -1)
				{
					UsersRow tmpRow = new UsersRow();
					tmpRow.user_id = MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_id;
					
					tmpRow.user_login = MyCinemaSellerView.sellerView.users_login.getText();
					tmpRow.user_pass = MyCinemaSellerView.sellerView.users_pass.getText();
					
					if(MyCinemaSellerView.sellerView.users_type.getSelectedIndex() == 0)
					{
						tmpRow.user_type = "CUSTOMER";
					}else{
						tmpRow.user_type = "SELLER";	
					}
					
					tmpRow.user_name = MyCinemaSellerView.sellerView.users_name.getText();
					tmpRow.user_surname = MyCinemaSellerView.sellerView.users_surname.getText();
					tmpRow.user_age = Integer.parseInt(MyCinemaSellerView.sellerView.users_age.getText());
					//MyCinemaSellerView.sellerView.users_age.get
					MyCinemaSellerView.sellerView.myCinemaController.UpdateUser(tmpRow);
					
					//update list
					MyCinemaSellerView.sellerView.userList = MyCinemaSellerView.sellerView.myCinemaController.GetUsersList();
					MyCinemaSellerView.sellerView.users_userList.setListData(MyCinemaController.UsersRowListToArray(MyCinemaSellerView.sellerView.userList));
				}else{
					JOptionPane.showMessageDialog(null, "Pick user to update...");
				}
			};
		};
		users_buttonUpdate.addActionListener(actionUpdate);
		tabUsers.add(users_buttonUpdate);
		
		users_buttonDeselect = new JButton("Deselect");
		users_buttonDeselect.setBounds(620, 100, 100, 40);
		ActionListener actionDeselect = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MyCinemaSellerView.sellerView.users_userList.clearSelection();
			}
		};
		users_buttonDeselect.addActionListener(actionDeselect);
		tabUsers.add(users_buttonDeselect);
		
		users_buttonAdd = new JButton("Add");
		users_buttonAdd.setBounds(620, 150, 100, 40);
		ActionListener actionAdd = new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				int condition = 0;
				if(MyCinemaSellerView.sellerView.users_login.getText().equals(""))
					condition = 2;
				if(MyCinemaSellerView.sellerView.users_pass.getText().equals(""))
					condition = 3;
				if(MyCinemaSellerView.sellerView.users_name.getText().equals(""))
					condition = 4;
				if(MyCinemaSellerView.sellerView.users_surname.getText().equals(""))
					condition = 5;
				if(MyCinemaSellerView.sellerView.users_age.getText().equals(""))
					condition = 6;
				if(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex() != -1)
					condition = 1;
				if(condition == 0)
				{
					UsersRow tmpRow = new UsersRow();
					tmpRow.user_id = -1;
					tmpRow.user_login = MyCinemaSellerView.sellerView.users_login.getText();
					tmpRow.user_pass = MyCinemaSellerView.sellerView.users_pass.getText();
					
					if(MyCinemaSellerView.sellerView.users_type.getSelectedIndex() == 0)
					{
						tmpRow.user_type = "CUSTOMER";
					}else{
						tmpRow.user_type = "SELLER";	
					}
					
					tmpRow.user_name = MyCinemaSellerView.sellerView.users_name.getText();
					tmpRow.user_surname = MyCinemaSellerView.sellerView.users_surname.getText();
					try{
						tmpRow.user_age = Integer.parseInt(MyCinemaSellerView.sellerView.users_age.getText());
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "You have to enter a valin integer number for age field.");
						return;
					}
					MyCinemaSellerView.sellerView.myCinemaController.AddUser(tmpRow);
					
					//update list
					MyCinemaSellerView.sellerView.userList = MyCinemaSellerView.sellerView.myCinemaController.GetUsersList();
					MyCinemaSellerView.sellerView.users_userList.setListData(MyCinemaController.UsersRowListToArray(MyCinemaSellerView.sellerView.userList));
					
					MyCinemaSellerView.sellerView.users_userList.clearSelection();
					
					MyCinemaSellerView.sellerView.users_login.setText("");
					MyCinemaSellerView.sellerView.users_pass.setText("");
					MyCinemaSellerView.sellerView.users_type.setSelectedIndex(0);					
					MyCinemaSellerView.sellerView.users_name.setText("");
					MyCinemaSellerView.sellerView.users_surname.setText("");
					MyCinemaSellerView.sellerView.users_age.setText("");
				}else{
					if(condition == 1)
						JOptionPane.showMessageDialog(null, "You have to deselect.");
					else
						JOptionPane.showMessageDialog(null, "You have to fill all the fields...");
				}
			}
		};
		users_buttonAdd.addActionListener(actionAdd);
		tabUsers.add(users_buttonAdd);
		
		users_buttonDelete = new JButton("Delete");
		users_buttonDelete.setBounds(620, 200, 100, 40);
		ActionListener actionDelete = new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex() != -1)
				{
					int answer = JOptionPane.showConfirmDialog(null, "Do you really want to delete user?\n" + MyCinemaSellerView.sellerView.users_userList.getSelectedValue(), "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == JOptionPane.YES_OPTION)
					{
						int id = MyCinemaSellerView.sellerView.userList.get(MyCinemaSellerView.sellerView.users_userList.getSelectedIndex()).user_id;
						MyCinemaSellerView.sellerView.myCinemaController.DeleteUser(id);
						//update list
						MyCinemaSellerView.sellerView.userList = MyCinemaSellerView.sellerView.myCinemaController.GetUsersList();
						MyCinemaSellerView.sellerView.users_userList.setListData(MyCinemaController.UsersRowListToArray(MyCinemaSellerView.sellerView.userList));
						
						MyCinemaSellerView.sellerView.users_userList.clearSelection();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick user to delete...");
				}
			}
		};
		users_buttonDelete.addActionListener(actionDelete);
		tabUsers.add(users_buttonDelete);
	}
	private void initializeTabMovies(JTabbedPane jTabbedPane)
	{
		tabMovies = new JPanel();
		tabMovies.setName("Movies");
		tabMovies.setLayout(null);
		tabMovies.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabMovies);
		
		
		this.movies_movieList = new JList<String>();
		this.movies_movieList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.movies_scrollList = new JScrollPane();
		this.movies_scrollList.setBounds(50, 50, 350, 270);
		this.movies_scrollList.setVisible(true);
		movies_scrollList.setViewportView(movies_movieList);
		tabMovies.add(movies_scrollList);
		this.movieList = this.myCinemaController.GetMoviesList();
		this.movies_movieList.setListData(MyCinemaController.MoviesRowListToArray(this.movieList));
		
		JLabel movies_title_label = new JLabel("Title:");
		movies_title_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_title_label.setBounds(410, 50,100,20);
		movies_title_label.setVisible(true);
		tabMovies.add(movies_title_label);
		this.movies_title = new JTextField();
		this.movies_title.setBounds(510, 50, 100, 20);
		tabMovies.add(movies_title);
		
		JLabel movies_lenth_label = new JLabel("Lenth:");
		movies_lenth_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_lenth_label.setBounds(410, 80,100,20);
		movies_lenth_label.setVisible(true);
		tabMovies.add(movies_lenth_label);
		this.movies_lenth = new JTextField();
		this.movies_lenth.setBounds(510, 80, 100, 20);
		tabMovies.add(movies_lenth);
		
		JLabel movies_min_age_label = new JLabel("Minimum age:");
		movies_min_age_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_min_age_label.setBounds(410, 110,100,20);
		movies_min_age_label.setVisible(true);
		tabMovies.add(movies_min_age_label);
		this.movies_min_age = new JTextField();
		this.movies_min_age.setBounds(510, 110, 100, 20);
		tabMovies.add(movies_min_age);
		
		JLabel movies_price_label = new JLabel("Ticket price:");
		movies_price_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_price_label.setBounds(410, 140,100,20);
		movies_price_label.setVisible(true);
		tabMovies.add(movies_price_label);
		this.movies_price = new JTextField();
		this.movies_price.setBounds(510, 140, 100, 20);
		tabMovies.add(movies_price);
		
		JLabel movies_genre_label = new JLabel("Genre:");
		movies_genre_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_genre_label.setBounds(410, 170,100,20);
		movies_genre_label.setVisible(true);
		tabMovies.add(movies_genre_label);
		this.movies_genre = new JComboBox();
		this.movies_genre.setBounds(510, 170, 100, 20);
		this.movies_genre.addItem("ACTION");
		this.movies_genre.addItem("FAMILY");
		this.movies_genre.addItem("COMEDY");
		tabMovies.add(movies_genre);
		
		JLabel movies_licence_price_label = new JLabel("Licence price:");
		movies_licence_price_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_licence_price_label.setBounds(410, 200,100,20);
		movies_licence_price_label.setVisible(true);
		tabMovies.add(movies_licence_price_label);
		this.movies_licence_price = new JTextField();
		this.movies_licence_price.setBounds(510, 200, 100, 20);
		tabMovies.add(movies_licence_price);
		
		JLabel movies_licence_payment_day_label = new JLabel("Payment day:");
		movies_licence_payment_day_label.setHorizontalAlignment(SwingConstants.RIGHT);
		movies_licence_payment_day_label.setBounds(410, 230,100,20);
		movies_licence_payment_day_label.setVisible(true);
		tabMovies.add(movies_licence_payment_day_label);
		this.movies_licence_payment_day = new JTextField();
		this.movies_licence_payment_day.setBounds(510, 230, 100, 20);
		tabMovies.add(movies_licence_payment_day);
		
		movies_buttonUpdate = new JButton("Update");
		movies_buttonUpdate.setBounds(620, 50, 100, 40);
		tabMovies.add(movies_buttonUpdate);
		
		movies_buttonDeselect = new JButton("Deselect");
		movies_buttonDeselect.setBounds(620, 100, 100, 40);
		tabMovies.add(movies_buttonDeselect);
		
		movies_buttonAdd = new JButton("Add");
		movies_buttonAdd.setBounds(620, 150, 100, 40);
		tabMovies.add(movies_buttonAdd);
		
		movies_buttonDelete = new JButton("Delete");
		movies_buttonDelete.setBounds(620, 200, 100, 40);
		tabMovies.add(movies_buttonDelete);
		
		ListSelectionListener actionIndexChanged = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex() != -1)
				{
					MyCinemaSellerView.sellerView.movies_title.setText(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_title);
					MyCinemaSellerView.sellerView.movies_lenth.setText(String.valueOf(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_lenth));
					MyCinemaSellerView.sellerView.movies_min_age.setText(String.valueOf(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_min_age));
					MyCinemaSellerView.sellerView.movies_price.setText(String.valueOf(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_price));
					
					
					switch(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_genre)
					{
					case "":
						MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(-1);
						break;
					case "ACTION":
						MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(0);
						break;
					case "FAMILY":
						MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(1);
						break;
					case "COMEDY":
						MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(2);
						break;
					}					
					MyCinemaSellerView.sellerView.movies_licence_price.setText(String.valueOf(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_licence_price));
					MyCinemaSellerView.sellerView.movies_licence_payment_day.setText(String.valueOf(MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_licence_payment_day));
				}else{
					MyCinemaSellerView.sellerView.movies_title.setText("");
					MyCinemaSellerView.sellerView.movies_lenth.setText("");
					MyCinemaSellerView.sellerView.movies_min_age.setText("");
					MyCinemaSellerView.sellerView.movies_price.setText("");
					MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(0);					
					MyCinemaSellerView.sellerView.movies_licence_price.setText("");
					MyCinemaSellerView.sellerView.movies_licence_payment_day.setText("");
				}
			}
		};
		this.movies_movieList.addListSelectionListener(actionIndexChanged);
		
		ActionListener actionUpdate = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex() != -1)
				{
					try{
					MoviesRow tmpRow = new MoviesRow();
					tmpRow.movie_id = MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_id;
					
					tmpRow.movie_title = MyCinemaSellerView.sellerView.movies_title.getText();
					tmpRow.movie_lenth = Integer.parseInt(MyCinemaSellerView.sellerView.movies_lenth.getText());
					tmpRow.movie_min_age = Integer.parseInt(MyCinemaSellerView.sellerView.movies_min_age.getText());
					tmpRow.movie_price = Double.parseDouble(MyCinemaSellerView.sellerView.movies_price.getText());
					
					tmpRow.movie_genre = String.valueOf(MyCinemaSellerView.sellerView.movies_genre.getSelectedItem());
					
					tmpRow.movie_licence_price = Double.parseDouble(MyCinemaSellerView.sellerView.movies_licence_price.getText());
					tmpRow.movie_licence_payment_day = Integer.parseInt(MyCinemaSellerView.sellerView.movies_licence_payment_day.getText());
					//MyCinemaSellerView.sellerView.users_age.get
					MyCinemaSellerView.sellerView.myCinemaController.UpdateMovie(tmpRow);
					
					//update list
					MyCinemaSellerView.sellerView.movieList = MyCinemaSellerView.sellerView.myCinemaController.GetMoviesList();
					MyCinemaSellerView.sellerView.movies_movieList.setListData(MyCinemaController.MoviesRowListToArray(MyCinemaSellerView.sellerView.movieList));
					}
					catch(NumberFormatException en)
					{
						JOptionPane.showMessageDialog(null, "Put in correct data formats...");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick movie to update...");
				}
			};
		};
		this.movies_buttonUpdate.addActionListener(actionUpdate);
		
		ActionListener actionDeselect = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.movies_movieList.clearSelection();
			}
		};
		this.movies_buttonDeselect.addActionListener(actionDeselect);
		
		ActionListener actionAdd = new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				int condition = 0;
				if(MyCinemaSellerView.sellerView.movies_title.getText().equals(""))
					condition = 2;
				if(MyCinemaSellerView.sellerView.movies_lenth.getText().equals(""))
					condition = 3;
				if(MyCinemaSellerView.sellerView.movies_min_age.getText().equals(""))
					condition = 4;
				if(MyCinemaSellerView.sellerView.movies_price.getText().equals(""))
					condition = 5;
				if(MyCinemaSellerView.sellerView.movies_licence_price.getText().equals(""))
					condition = 6;
				if(MyCinemaSellerView.sellerView.movies_licence_payment_day.getText().equals(""))
					condition = 7;
				if(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex() != -1)
					condition = 1;
				if(condition == 0)
				{
					try{
						MoviesRow tmpRow = new MoviesRow();
						tmpRow.movie_id = -1;
						tmpRow.movie_title = MyCinemaSellerView.sellerView.movies_title.getText();
						tmpRow.movie_lenth = Integer.parseInt(MyCinemaSellerView.sellerView.movies_lenth.getText());
						tmpRow.movie_min_age = Integer.parseInt(MyCinemaSellerView.sellerView.movies_min_age.getText());
						tmpRow.movie_price = Double.parseDouble(MyCinemaSellerView.sellerView.movies_price.getText());
						
						tmpRow.movie_genre = String.valueOf(MyCinemaSellerView.sellerView.movies_genre.getSelectedItem());
						
						tmpRow.movie_licence_price = Double.parseDouble(MyCinemaSellerView.sellerView.movies_licence_price.getText());
						tmpRow.movie_licence_payment_day = Integer.parseInt(MyCinemaSellerView.sellerView.movies_licence_payment_day.getText());
						
						MyCinemaSellerView.sellerView.myCinemaController.AddMovie(tmpRow);
						
						//update list
						MyCinemaSellerView.sellerView.movieList = MyCinemaSellerView.sellerView.myCinemaController.GetMoviesList();
						MyCinemaSellerView.sellerView.movies_movieList.setListData(MyCinemaController.MoviesRowListToArray(MyCinemaSellerView.sellerView.movieList));
						
						MyCinemaSellerView.sellerView.movies_movieList.clearSelection();
						
						MyCinemaSellerView.sellerView.movies_title.setText("");
						MyCinemaSellerView.sellerView.movies_lenth.setText("");
						MyCinemaSellerView.sellerView.movies_min_age.setText("");
						MyCinemaSellerView.sellerView.movies_price.setText("");
						MyCinemaSellerView.sellerView.movies_genre.setSelectedIndex(0);					
						MyCinemaSellerView.sellerView.movies_licence_price.setText("");
						MyCinemaSellerView.sellerView.movies_licence_payment_day.setText("");
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "You have to enter a valid numeric values in coresponding fields.");
						return;
					}
				}else{
					if(condition == 1)
						JOptionPane.showMessageDialog(null, "You have to deselect.");
					else
						JOptionPane.showMessageDialog(null, "You have to fill all the fields...");
				}
			}
		};
		movies_buttonAdd.addActionListener(actionAdd);
		
		ActionListener actionDelete = new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex() != -1)
				{
					int answer = JOptionPane.showConfirmDialog(null, "Do you really want to delete movie?\n" + MyCinemaSellerView.sellerView.movies_movieList.getSelectedValue(), "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == JOptionPane.YES_OPTION)
					{
						int id = MyCinemaSellerView.sellerView.movieList.get(MyCinemaSellerView.sellerView.movies_movieList.getSelectedIndex()).movie_id;
						MyCinemaSellerView.sellerView.myCinemaController.DeleteMovie(id);
						//update list
						MyCinemaSellerView.sellerView.movieList = MyCinemaSellerView.sellerView.myCinemaController.GetMoviesList();
						MyCinemaSellerView.sellerView.movies_movieList.setListData(MyCinemaController.MoviesRowListToArray(MyCinemaSellerView.sellerView.movieList));
						
						MyCinemaSellerView.sellerView.movies_movieList.clearSelection();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick movie to delete...");
				}
			}
		};
		movies_buttonDelete.addActionListener(actionDelete);
	}
	private void initializeTabShows(JTabbedPane jTabbedPane)
	{
		tabShows = new JPanel();
		tabShows.setName("Shows");
		tabShows.setLayout(null);
		tabShows.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabShows);
		
		this.shows_movieListJList = new JList<String>();
		this.shows_movieListJList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.shows_movie_scrollList = new JScrollPane();
		this.shows_movie_scrollList.setBounds(50, 50, 250, 270);
		this.shows_movie_scrollList.setVisible(true);
		shows_movie_scrollList.setViewportView(shows_movieListJList);
		tabShows.add(shows_movie_scrollList);
		this.shows_movieListArray = this.myCinemaController.GetMoviesList();
		this.shows_movieListJList.setListData(MyCinemaController.MoviesRowListToArray(this.shows_movieListArray));
		
		this.shows_showList = new JList<String>();
		this.shows_showList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.shows_scrollList = new JScrollPane();
		this.shows_scrollList.setBounds(500, 50, 250, 270);
		this.shows_scrollList.setVisible(true);
		shows_scrollList.setViewportView(shows_showList);
		tabShows.add(shows_scrollList);
		this.showList = this.myCinemaController.GetShowsList();
		this.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(this.showList));
		
		ListSelectionListener actionIndexChanged_movies = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex() != -1)
				{
					MyCinemaSellerView.sellerView.showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList(MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id);
					MyCinemaSellerView.sellerView.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.showList));
				}
				else{
					MyCinemaSellerView.sellerView.showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList();
					MyCinemaSellerView.sellerView.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.showList));
				}
			}
		};
		this.shows_movieListJList.addListSelectionListener(actionIndexChanged_movies);
		
		ListSelectionListener actionIndexChanged_shows = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				int index = MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex();
				if(index != -1)
					MyCinemaSellerView.sellerView.shows_date.setText(MyCinemaSellerView.sellerView.showList.get(index).show_date.toString());
				else
					MyCinemaSellerView.sellerView.shows_date.setText("");
			}
		};
		this.shows_showList.addListSelectionListener(actionIndexChanged_shows);
		
		JLabel shows_date_label = new JLabel("<html>Insert date in format:<br>YYYY-MM-DD HH:MM:SS");
		shows_date_label.setHorizontalAlignment(SwingConstants.CENTER);
		shows_date_label.setBounds(320, 250, 160, 40);
		shows_date_label.setVisible(true);
		tabShows.add(shows_date_label);
		this.shows_date = new JTextField();
		this.shows_date.setBounds(320, 290, 160, 20);
		tabShows.add(shows_date);
		
		shows_buttonDeselectMovie = new JButton("Deselect");
		shows_buttonDeselectMovie.setBounds(305, 50, 90, 40);
		this.tabShows.add(shows_buttonDeselectMovie);
		ActionListener actionDeselectMovie = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.shows_movieListJList.clearSelection();
			}
		};
		shows_buttonDeselectMovie.addActionListener(actionDeselectMovie);
		
		shows_buttonDeselectShow = new JButton("Deselect");
		shows_buttonDeselectShow.setBounds(405, 50, 90, 40);
		this.tabShows.add(shows_buttonDeselectShow);
		ActionListener actionDeselectShow = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.shows_showList.clearSelection();
			}
		};
		shows_buttonDeselectShow.addActionListener(actionDeselectShow);
		
		shows_buttonAdd = new JButton("Add");
		shows_buttonAdd.setBounds(405, 100, 90, 40);
		this.tabShows.add(shows_buttonAdd);
		ActionListener actionAdd = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex() != -1)
				{
					if(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex() == -1)
					{
						if(!MyCinemaSellerView.sellerView.shows_date.getText().equals(""))
						{
							try
							{
								ShowsRow tmpRow = new ShowsRow();
								tmpRow.show_date =  Timestamp.valueOf(MyCinemaSellerView.sellerView.shows_date.getText());
								tmpRow.show_movie_id = MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id;
								MyCinemaSellerView.sellerView.myCinemaController.AddShow(tmpRow);
								
								MyCinemaSellerView.sellerView.showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList(MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id);
								MyCinemaSellerView.sellerView.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.showList));
							}
							catch(IllegalArgumentException e)
							{
								JOptionPane.showMessageDialog(null, "Data field has to be in the specyfied text format.");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Fill in the date field.");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Before adding new show deselect existing.");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick movie to create show for it.");
				}
			}
		};
		shows_buttonAdd.addActionListener(actionAdd);
		
		shows_buttonUpdate = new JButton("Update");
		shows_buttonUpdate.setBounds(405, 150, 90, 40);
		this.tabShows.add(shows_buttonUpdate);
		ActionListener actionUpdate = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex() != -1)
				{
					if(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex() != -1)
					{
						if(!MyCinemaSellerView.sellerView.shows_date.getText().equals(""))
						{
							try
							{
								ShowsRow tmpRow = new ShowsRow();
								tmpRow.show_date =  Timestamp.valueOf(MyCinemaSellerView.sellerView.shows_date.getText());
								tmpRow.show_movie_id = MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id;
								tmpRow.show_id = MyCinemaSellerView.sellerView.showList.get(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex()).show_id;
								MyCinemaSellerView.sellerView.myCinemaController.UpdateShow(tmpRow);
								
								MyCinemaSellerView.sellerView.showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList(MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id);
								MyCinemaSellerView.sellerView.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.showList));
							}
							catch(IllegalArgumentException e)
							{
								JOptionPane.showMessageDialog(null, "Data field has to be in the specyfied text format.");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Fill in the date field.");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Select show to update.");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick movie of which you want to update show.");
				}
			}
		};
		shows_buttonUpdate.addActionListener(actionUpdate);
		
		shows_buttonDelete = new JButton("Delete");
		shows_buttonDelete.setBounds(405, 200, 90, 40);
		this.tabShows.add(shows_buttonDelete);
		ActionListener actionDelete = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex() != -1)
				{
					String msg  = new String();
					msg += "Do you really want to delete show?\n";
					msg += MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedValue() + "\n";
					msg += MyCinemaSellerView.sellerView.showList.get(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex()).show_date.toString();
					int answer = JOptionPane.showConfirmDialog(null,msg , "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == JOptionPane.YES_OPTION)
					{
						int id = MyCinemaSellerView.sellerView.showList.get(MyCinemaSellerView.sellerView.shows_showList.getSelectedIndex()).show_id;
						MyCinemaSellerView.sellerView.myCinemaController.DeleteShow(id);
						//update list
						MyCinemaSellerView.sellerView.showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList(MyCinemaSellerView.sellerView.shows_movieListArray.get(MyCinemaSellerView.sellerView.shows_movieListJList.getSelectedIndex()).movie_id);
						MyCinemaSellerView.sellerView.shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.showList));
						
						MyCinemaSellerView.sellerView.users_userList.clearSelection();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Pick show to delete...");
				}
			}
		};
		shows_buttonDelete.addActionListener(actionDelete);
		
		this.shows_buttonToXml = new JButton("ToXml");
		this.shows_buttonToXml.setBounds(350, 340, 100, 20);
		this.tabShows.add(shows_buttonToXml);
		
		this.shows_fileName = new JTextField();
		this.shows_fileName.setBounds(350, 320, 100, 20);
		this.tabShows.add(shows_fileName);
		
		ActionListener actionToXml = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!MyCinemaSellerView.sellerView.shows_fileName.getText().equals(""))
				{
					MyCinemaSellerView.sellerView.myCinemaController.SaveRepertoireToXML(MyCinemaSellerView.sellerView.shows_fileName.getText());
				}else{
					JOptionPane.showMessageDialog(null, "Type in filename");
				}
			}
		};
		this.shows_buttonToXml.addActionListener(actionToXml);
	}
	private void initializeTabTickets(JTabbedPane jTabbedPane)
	{
		tabTickets = new JPanel();
		tabTickets.setName("Tickets");
		tabTickets.setLayout(null);
		tabTickets.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabTickets);
		
		this.tickets_shows_movieListJList = new JList<String>();
		this.tickets_shows_movieListJList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.tickets_shows_movie_scrollList = new JScrollPane();
		this.tickets_shows_movie_scrollList.setBounds(50, 50, 200, 150);
		this.tickets_shows_movie_scrollList.setVisible(true);
		tickets_shows_movie_scrollList.setViewportView(tickets_shows_movieListJList);
		tabTickets.add(tickets_shows_movie_scrollList);
		this.tickets_shows_movieListArray = this.myCinemaController.GetMoviesList();
		this.tickets_shows_movieListJList.setListData(MyCinemaController.MoviesRowListToArray(this.tickets_shows_movieListArray));
		
		this.tickets_shows_showList = new JList<String>();
		this.tickets_shows_showList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.tickets_shows_scrollList = new JScrollPane();
		this.tickets_shows_scrollList.setBounds(275, 50, 150, 150);
		this.tickets_shows_scrollList.setVisible(true);
		this.tickets_shows_scrollList.setViewportView(tickets_shows_showList);
		tabTickets.add(tickets_shows_scrollList);
		this.tickets_showList = this.myCinemaController.GetShowsList();
		this.tickets_shows_showList.setListData(MyCinemaController.ShowsRowListToArray(this.tickets_showList));
		
		ListSelectionListener actionIndexChanged_movies = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex() != -1)
				{
					//update shows
					MyCinemaSellerView.sellerView.tickets_showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
					MyCinemaSellerView.sellerView.tickets_shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.tickets_showList));
					
					//update tickets
					MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsListForMovie(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
					MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
				}
				else{
					MyCinemaSellerView.sellerView.tickets_showList = MyCinemaSellerView.sellerView.myCinemaController.GetShowsList();
					MyCinemaSellerView.sellerView.tickets_shows_showList.setListData(MyCinemaController.ShowsRowListToArray(MyCinemaSellerView.sellerView.tickets_showList));
					
					MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList();
					MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
				}
			}
		};
		this.tickets_shows_movieListJList.addListSelectionListener(actionIndexChanged_movies);
		
		this.tickets_ticketList = new JList<String>();
		this.tickets_ticketList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.tickets_scrollList = new JScrollPane();
		this.tickets_scrollList.setBounds(450, 50, 300, 150);
		this.tickets_scrollList.setVisible(true);
		tickets_scrollList.setViewportView(tickets_ticketList);
		tabTickets.add(tickets_scrollList);
		this.ticketList = this.myCinemaController.GetTicketsList();
		this.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(this.ticketList));

		ListSelectionListener actionIndexChanged_shows = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				//update
				if(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex() != -1)
				{
					MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList(MyCinemaSellerView.sellerView.tickets_showList.get(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex()).show_id);
					MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
				}
				else{
					if(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex() != -1)
					{
						MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsListForMovie(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
						MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
					}else{
						MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList();
						MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
					}
				}
			}
		};
		this.tickets_shows_showList.addListSelectionListener(actionIndexChanged_shows);
		
		this.tickets_buttonDeselectMovies = new JButton("Deselect");
		this.tickets_buttonDeselectMovies.setBounds( 50, 20, 100,20);
		this.tabTickets.add(tickets_buttonDeselectMovies);
		ActionListener actionDeselectMovies = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.tickets_shows_movieListJList.clearSelection();
			}
		};
		this.tickets_buttonDeselectMovies.addActionListener(actionDeselectMovies);
		
		this.tickets_buttonDeselectShows = new JButton("Deselect");
		this.tickets_buttonDeselectShows.setBounds( 275, 20, 100,20);
		this.tabTickets.add(tickets_buttonDeselectShows);
		ActionListener actionDeselectShows = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.tickets_shows_showList.clearSelection();
			}
		};
		this.tickets_buttonDeselectShows.addActionListener(actionDeselectShows);
		
		JLabel user_id_label = new JLabel("User id");
		user_id_label.setBounds(450, 230, 90, 20);
		this.tabTickets.add(user_id_label);
		this.tickets_user_id = new JTextField();
		this.tickets_user_id.setBounds(450, 250, 90, 20);
		this.tabTickets.add(tickets_user_id);
		
		JLabel seat_number_label = new JLabel("Seat number");
		seat_number_label.setBounds(550, 230, 90, 20);
		this.tabTickets.add(seat_number_label);
		this.tickets_seat_number = new JTextField();
		this.tickets_seat_number.setBounds(550, 250, 90, 20);
		this.tabTickets.add(tickets_seat_number);
		
		JLabel state_label = new JLabel("Ticket state");
		state_label.setBounds(650, 230, 90, 20);
		this.tabTickets.add(state_label);
		this.tickets_state = new JComboBox<String>();
		this.tickets_state.setBounds(650, 250, 90, 20);
		this.tickets_state.addItem("RESERVATION");
		this.tickets_state.addItem("BOUGHT");
		this.tickets_state.addItem("USED");
		this.tabTickets.add(tickets_state);
		
		ListSelectionListener actionIndexChanged_tickets = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex() != -1)
				{
					
					TicketsRow tmpRow = MyCinemaSellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex());
					MyCinemaSellerView.sellerView.tickets_user_id.setText(String.valueOf(tmpRow.ticket_user_id));
					MyCinemaSellerView.sellerView.tickets_seat_number.setText(String.valueOf(tmpRow.ticket_seat_number));
					MyCinemaSellerView.sellerView.tickets_state.setSelectedItem(String.valueOf(tmpRow.ticket_state));
				}
				else{
					MyCinemaSellerView.sellerView.tickets_user_id.setText("");
					MyCinemaSellerView.sellerView.tickets_seat_number.setText("");
					MyCinemaSellerView.sellerView.tickets_state.setSelectedItem("RESERVATION");
				}
			}
		};
		this.tickets_ticketList.addListSelectionListener(actionIndexChanged_tickets);
		
		this.tickets_buttonUpdate = new JButton("Update");
		this.tickets_buttonUpdate.setBounds( 550, 280, 90,20);
		this.tabTickets.add(tickets_buttonUpdate);
		ActionListener actionTicketUpdate = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(null, "Pick ticket to update");
					return;
				}
				if(MyCinemaSellerView.sellerView.tickets_user_id.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill in the user id field.");
					return;
				}
				if(MyCinemaSellerView.sellerView.tickets_seat_number.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill in the seat number field.");
					return;
				}
				try
				{
					TicketsRow tmpRow = MyCinemaSellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex());
					tmpRow.ticket_user_id = Integer.parseInt(MyCinemaSellerView.sellerView.tickets_user_id.getText());
					int oldSeatNumber = tmpRow.ticket_seat_number;
					tmpRow.ticket_seat_number = Integer.parseInt(MyCinemaSellerView.sellerView.tickets_seat_number.getText());
					tmpRow.ticket_state = String.valueOf(MyCinemaSellerView.sellerView.tickets_state.getSelectedItem());
					
					int result = MyCinemaSellerView.sellerView.myCinemaController.UpdateTicket(tmpRow, oldSeatNumber);
					if(result == 0)
					{
						//update
						if(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList(MyCinemaSellerView.sellerView.tickets_showList.get(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex()).show_id);
							MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
						}
						else{
							if(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex() != -1)
							{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsListForMovie(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}else{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList();
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}
						}
					}else{
						JOptionPane.showMessageDialog(null, "Pick diffrent seat number");
					}
				}
				catch(NumberFormatException en)
				{
					JOptionPane.showMessageDialog(null,"Fill in fields with apropriate numbers.");
				}
			}
		};
		this.tickets_buttonUpdate.addActionListener(actionTicketUpdate);
		
		this.tickets_buttonDelete = new JButton("Delete");
		this.tickets_buttonDelete.setBounds(650 , 280, 90,20);
		this.tabTickets.add(tickets_buttonDelete);
		ActionListener actionTicketDelete = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex() != -1)
				{
					int ticket_id = MyCinemaSellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex()).ticket_id;
					String msg = "";
					msg += "Are you sure you want to delete ticket?";
					msg += "\nId: " + MyCinemaSellerView.sellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex()).ticket_id;
					msg += " for user: " + MyCinemaSellerView.sellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex()).ticket_user_id;
					msg += " on steat: " + MyCinemaSellerView.sellerView.sellerView.ticketList.get(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex()).ticket_seat_number;
					int answer = JOptionPane.showConfirmDialog(null,msg , "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == JOptionPane.YES_OPTION)
					{
						MyCinemaSellerView.sellerView.myCinemaController.DeleteTicket(ticket_id);
						//update
						if(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList(MyCinemaSellerView.sellerView.tickets_showList.get(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex()).show_id);
							MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
						}
						else{
							if(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex() != -1)
							{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsListForMovie(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}else{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList();
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Pick ticket to delete.");
				}
			}
		};
		this.tickets_buttonDelete.addActionListener(actionTicketDelete);
		
		this.tickets_buttonAdd = new JButton("Add");
		this.tickets_buttonAdd.setBounds(450, 280, 90, 20);
		this.tabTickets.add(tickets_buttonAdd);
		ActionListener actionTicketAdd = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MyCinemaSellerView.sellerView.tickets_ticketList.getSelectedIndex() != -1)
				{
					JOptionPane.showMessageDialog(null, "You have to deselect existing ticket to add one.");
					return;
				}
				if(MyCinemaSellerView.sellerView.tickets_user_id.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill in the user id field.");
					return;
				}
				if(MyCinemaSellerView.sellerView.tickets_seat_number.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill in the seat number field.");
					return;
				}
				try
				{
					TicketsRow tmpRow = new TicketsRow();
					tmpRow.ticket_id = -1;
					tmpRow.ticket_user_id = Integer.parseInt(MyCinemaSellerView.sellerView.tickets_user_id.getText());
					tmpRow.ticket_seat_number = Integer.parseInt(MyCinemaSellerView.sellerView.tickets_seat_number.getText());
					tmpRow.ticket_state = String.valueOf(MyCinemaSellerView.sellerView.tickets_state.getSelectedItem());
					tmpRow.ticket_show_id = MyCinemaSellerView.sellerView.tickets_showList.get(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex()).show_id;
					tmpRow.ticket_discount = "";
					
					int result = MyCinemaSellerView.sellerView.myCinemaController.AddTicket(tmpRow);
					if(result == 0)
					{
						//update
						if(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList(MyCinemaSellerView.sellerView.tickets_showList.get(MyCinemaSellerView.sellerView.tickets_shows_showList.getSelectedIndex()).show_id);
							MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
						}
						else{
							if(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex() != -1)
							{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsListForMovie(MyCinemaSellerView.sellerView.tickets_shows_movieListArray.get(MyCinemaSellerView.sellerView.tickets_shows_movieListJList.getSelectedIndex()).movie_id);
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}else{
								MyCinemaSellerView.sellerView.ticketList = MyCinemaSellerView.sellerView.myCinemaController.GetTicketsList();
								MyCinemaSellerView.sellerView.tickets_ticketList.setListData(MyCinemaController.TicketsRowListToArray(MyCinemaSellerView.sellerView.ticketList));
							}
						}
					}else{
						JOptionPane.showMessageDialog(null, "Pick diffrent seat number");
					}
				}
				catch(NumberFormatException en)
				{
					JOptionPane.showMessageDialog(null,"Fill in fields with apropriate numbers.");
				}
			}
		};
		this.tickets_buttonAdd.addActionListener(actionTicketAdd);
		
		this.tickets_buttonDeselectTickets = new JButton("Deselect");
		this.tickets_buttonDeselectTickets.setBounds( 450, 20, 100,20);
		this.tabTickets.add(tickets_buttonDeselectTickets);
		ActionListener actionDeselectTickets = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.tickets_ticketList.clearSelection();
			}
		};
		this.tickets_buttonDeselectTickets.addActionListener(actionDeselectTickets);
	}
	private void initializeTabFinancials(JTabbedPane jTabbedPane)
	{
		tabFinancials = new JPanel();
		tabFinancials.setName("Financials");
		tabFinancials.setLayout(null);
		tabFinancials.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabFinancials);
		
		this.financials_financialList = new JList<String>();
		this.financials_financialList.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		this.financials_scrollList = new JScrollPane();
		this.financials_scrollList.setBounds(50, 50, 450, 270);
		this.financials_scrollList.setVisible(true);
		this.financials_scrollList.setViewportView(financials_financialList);
		tabFinancials.add(financials_scrollList);
		this.financialList = this.myCinemaController.GetFinancialsList();
		this.financials_financialList.setListData(MyCinemaController.FinancialsRowListToArray(this.financialList));
		ListSelectionListener actionIndexChanged_financials = new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
				{
					MyCinemaSellerView.sellerView.financials_value.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_value));
					MyCinemaSellerView.sellerView.financials_date.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_date));
					MyCinemaSellerView.sellerView.financials_type.setSelectedItem(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_type));
					MyCinemaSellerView.sellerView.financials_description.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_description));
					
				}else{
					MyCinemaSellerView.sellerView.financials_value.setText("");
					MyCinemaSellerView.sellerView.financials_date.setText("");
					MyCinemaSellerView.sellerView.financials_type.setSelectedItem("LICENCE");
					MyCinemaSellerView.sellerView.financials_description.setText("");
				}
			}
		};
		this.financials_financialList.addListSelectionListener(actionIndexChanged_financials);
		
		JLabel financials_value_label = new JLabel("Value:");
		financials_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
		financials_value_label.setBounds(450, 50,100,20);
		financials_value_label.setVisible(true);
		tabFinancials.add(financials_value_label);
		this.financials_value = new JTextField();
		this.financials_value.setBounds(550, 50, 200, 20);
		tabFinancials.add(financials_value);
		
		JLabel financials_date_label = new JLabel("Date:");
		financials_date_label.setHorizontalAlignment(SwingConstants.RIGHT);
		financials_date_label.setBounds(450, 80,100,20);
		financials_date_label.setVisible(true);
		tabFinancials.add(financials_date_label);
		this.financials_date = new JTextField();
		this.financials_date.setBounds(550, 80, 200, 20);
		tabFinancials.add(financials_date);
		
		JLabel financials_type_label = new JLabel("Type:");
		financials_type_label.setHorizontalAlignment(SwingConstants.RIGHT);
		financials_type_label.setBounds(450, 110,100,20);
		financials_type_label.setVisible(true);
		tabFinancials.add(financials_type_label);
		this.financials_type = new JComboBox<String>();
		this.financials_type.addItem("LICENCE");
		this.financials_type.addItem("TICKET");
		this.financials_type.setEditable(false);
		this.financials_type.setBounds(550, 110, 200, 20);
		tabFinancials.add(financials_type);
		
		JLabel financials_description_label = new JLabel("Text:");
		financials_description_label.setHorizontalAlignment(SwingConstants.RIGHT);
		financials_description_label.setBounds(450, 140,100,20);
		financials_description_label.setVisible(true);
		tabFinancials.add(financials_description_label);
		this.financials_description = new JTextField();
		this.financials_description.setBounds(550, 140, 200, 20);
		tabFinancials.add(financials_description);
		
		this.financials_buttonDeselect = new JButton("Deselect");
		this.financials_buttonDeselect.setBounds(550, 171, 100, 20);
		tabFinancials.add(financials_buttonDeselect);
		ActionListener actionDeselect = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MyCinemaSellerView.sellerView.financials_financialList.clearSelection();
			}
		};
		this.financials_buttonDeselect.addActionListener(actionDeselect);
		
		this.financials_buttonUpdate = new JButton("Update");
		this.financials_buttonUpdate.setBounds(650, 171, 100, 20);
		tabFinancials.add(financials_buttonUpdate);
		ActionListener actionUpdate = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
				{
					try
					{
						FinancialsRow tmpRow = MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex());
						tmpRow.financials_value = Double.parseDouble(MyCinemaSellerView.sellerView.financials_value.getText());
						tmpRow.financials_date = Timestamp.valueOf(MyCinemaSellerView.sellerView.financials_date.getText());
						tmpRow.financials_type = String.valueOf(MyCinemaSellerView.sellerView.financials_type.getSelectedItem());
						tmpRow.financials_description = MyCinemaSellerView.sellerView.financials_description.getText();
						MyCinemaSellerView.sellerView.myCinemaController.UpdateFinancial(tmpRow);
						if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.financials_value.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_value));
							MyCinemaSellerView.sellerView.financials_date.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_date));
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_type));
							MyCinemaSellerView.sellerView.financials_description.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_description));
							
						}else{
							MyCinemaSellerView.sellerView.financials_value.setText("");
							MyCinemaSellerView.sellerView.financials_date.setText("");
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem("LICENCE");
							MyCinemaSellerView.sellerView.financials_description.setText("");
						}
						MyCinemaSellerView.sellerView.financialList = MyCinemaSellerView.sellerView.myCinemaController.GetFinancialsList();
						MyCinemaSellerView.sellerView.financials_financialList.setListData(MyCinemaController.FinancialsRowListToArray(MyCinemaSellerView.sellerView.financialList));
					}
					catch(Exception en)
					{
						JOptionPane.showMessageDialog(null, en.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Select financial you want to update");
				}
			}
		};
		this.financials_buttonUpdate.addActionListener(actionUpdate);
		
		this.financials_buttonAdd = new JButton("Add");
		this.financials_buttonAdd.setBounds(650, 202, 100, 20);
		tabFinancials.add(financials_buttonAdd);
		ActionListener actionAdd = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() == -1)
				{
					try
					{
						FinancialsRow tmpRow = new FinancialsRow();
						tmpRow.financials_id = -1;
						tmpRow.financials_value = Double.parseDouble(MyCinemaSellerView.sellerView.financials_value.getText());
						tmpRow.financials_date = Timestamp.valueOf(MyCinemaSellerView.sellerView.financials_date.getText());
						tmpRow.financials_type = String.valueOf(MyCinemaSellerView.sellerView.financials_type.getSelectedItem());
						tmpRow.financials_description = MyCinemaSellerView.sellerView.financials_description.getText();
						MyCinemaSellerView.sellerView.myCinemaController.AddFinancials(tmpRow);
						if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.financials_value.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_value));
							MyCinemaSellerView.sellerView.financials_date.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_date));
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_type));
							MyCinemaSellerView.sellerView.financials_description.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_description));
							
						}else{
							MyCinemaSellerView.sellerView.financials_value.setText("");
							MyCinemaSellerView.sellerView.financials_date.setText("");
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem("LICENCE");
							MyCinemaSellerView.sellerView.financials_description.setText("");
						}
						MyCinemaSellerView.sellerView.financialList = MyCinemaSellerView.sellerView.myCinemaController.GetFinancialsList();
						MyCinemaSellerView.sellerView.financials_financialList.setListData(MyCinemaController.FinancialsRowListToArray(MyCinemaSellerView.sellerView.financialList));
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Deselect to add new financial");
				}
			}
		};
		this.financials_buttonAdd.addActionListener(actionAdd);
		
		this.financials_buttonDelete = new JButton("Delete");
		this.financials_buttonDelete.setBounds(550, 202, 100, 20);
		tabFinancials.add(financials_buttonDelete);
		ActionListener actionDelete = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
				{
					String msg  = new String();
					msg += "Do you really want to delete financial?\n";
					msg += MyCinemaSellerView.sellerView.financials_financialList.getSelectedValue() + "\n";
					int answer = JOptionPane.showConfirmDialog(null,msg , "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == JOptionPane.YES_OPTION)
					{
						int id = MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_id;
						MyCinemaSellerView.sellerView.myCinemaController.DeleteFinancials(id);
						//update list
						if(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex() != -1)
						{
							MyCinemaSellerView.sellerView.financials_value.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_value));
							MyCinemaSellerView.sellerView.financials_date.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_date));
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_type));
							MyCinemaSellerView.sellerView.financials_description.setText(String.valueOf(MyCinemaSellerView.sellerView.financialList.get(MyCinemaSellerView.sellerView.financials_financialList.getSelectedIndex()).financials_description));
							
						}else{
							MyCinemaSellerView.sellerView.financials_value.setText("");
							MyCinemaSellerView.sellerView.financials_date.setText("");
							MyCinemaSellerView.sellerView.financials_type.setSelectedItem("LICENCE");
							MyCinemaSellerView.sellerView.financials_description.setText("");
						}
						MyCinemaSellerView.sellerView.financialList = MyCinemaSellerView.sellerView.myCinemaController.GetFinancialsList();
						MyCinemaSellerView.sellerView.financials_financialList.setListData(MyCinemaController.FinancialsRowListToArray(MyCinemaSellerView.sellerView.financialList));
					}
				}else{
					JOptionPane.showMessageDialog(null, "Select financial to delete.");
				}
			}
		};
		this.financials_buttonDelete.addActionListener(actionDelete);
	}
	private void initializeTabChart(JTabbedPane jTabbedPane)
	{
		tabChart = new JPanel();
		tabChart.setName("Chart");
		tabChart.setLayout(null);
		tabChart.setBackground(this.getBackground().brighter());
		jTabbedPane.add(tabChart);
		
		this.chart_panel = null;
		
		JLabel chartLabel = new JLabel("Enter year");
		chartLabel.setBounds(250, 340, 90, 20);
		chartLabel.setVisible(true);
		tabChart.add(chartLabel);
		this.chart_year = new JTextField();
		this.chart_year.setBounds(350, 340, 100, 20);
		this.chart_year.setVisible(true);
		tabChart.add(chart_year);
		
		this.chart_draw = new JButton("Draw");
		this.chart_draw.setBounds(450, 340, 100, 20);
		this.chart_draw.setVisible(true);
		tabChart.add(chart_draw);
		ActionListener actionChart = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!MyCinemaSellerView.sellerView.chart_year.getText().equals(""))
				{
					ChartItem[] tmpList;
					tmpList = MyCinemaSellerView.sellerView.myCinemaController.GetMonthlyFinancials(MyCinemaSellerView.sellerView.chart_year.getText());
					double[] xs = new double[12];
					double[] ys = new double[12];
					for(int i = 0;i< 12;i++)
					{
						xs[i] = tmpList[i].x;
						ys[i] = tmpList[i].y;
					}
					if(MyCinemaSellerView.sellerView.chart_panel != null)
						MyCinemaSellerView.sellerView.tabChart.remove(MyCinemaSellerView.sellerView.chart_panel);
					MyCinemaSellerView.sellerView.chart_panel = new LineChart(xs,"Months",ys,"","Financials",760,320,MyCinemaSellerView.sellerView.tabChart.getBackground().darker());
					MyCinemaSellerView.sellerView.chart_panel.setBounds(10, 10, 760, 320);
					MyCinemaSellerView.sellerView.tabChart.add(MyCinemaSellerView.sellerView.chart_panel);
					MyCinemaSellerView.sellerView.tabChart.revalidate();
				}else{
					JOptionPane.showMessageDialog(null, "Enter valid year");
				}
				
			}
		};
		this.chart_draw.addActionListener(actionChart);
	}
}