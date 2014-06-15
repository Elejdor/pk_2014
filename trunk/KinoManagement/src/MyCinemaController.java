import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.security.*;

import javax.swing.JOptionPane;

import com.mysql.jdbc.NonRegisteringDriver;

public class MyCinemaController 
{
	private Connection con;
	private Statement statement;
	public MyCinemaController()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql.devspot.home.pl","08692495_0000005","kompo123");
			statement = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Couldn't connect to database...");
		}
	}
	public boolean loginSeller(String login, String pass)
	{
		try 
		{
			String quary = new String("");
			quary += "SELECT * FROM 08692495_0000005.users WHERE (";
			quary += "user_login='"+login+"'";
			quary += " AND user_type='SELLER' )";
			ResultSet results = statement.executeQuery(quary);
			if(results.next())
			{
				String realPass = results.getString("user_pass");
				String hashedPass = Hash(pass.toLowerCase().toString());
			
				if(realPass.equals(pass))
					return true;
				else
					return false;
			}else{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Connection error");
			return false;
		}
	}
	public boolean loginCustomer(String login, String pass)
	{
		try 
		{
			String quary = new String("");
			quary += "Select * FROM 08692495_0000005.users WHERE (";
			quary += "user_login='"+login+"'";
			quary += " AND user_type='CUSTOMER' )";
			ResultSet results = statement.executeQuery(quary);
			if(results.next())
			{
				String realPass = results.getString("user_pass");
				String hashedPass = Hash(pass.toLowerCase().toString());
				//System.out.println(realPass + "  " + hashedPass);
				if(realPass.equals(pass))
					return true;
				else
					return false;
			}else{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Connection error");
			return false;
		}
	}
	public static String Hash(String input)
	{
		MessageDigest stringHasher = null;
		try {
			stringHasher = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[input.length()];
		if(stringHasher != null)
		{
			buffer = stringHasher.digest(input.getBytes());
		}
		return buffer.toString();
	}

	//users
	public ArrayList<UsersRow> GetUsersList()
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.users");
			int count = 0;
			UsersRow tmp = new UsersRow();
			ArrayList<UsersRow> tmpList = new ArrayList<UsersRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new UsersRow(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public UsersRow GetUser(int user_id)
	{
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.users WHERE user_name='" + user_id + "'");
			UsersRow result = new UsersRow();
			result = new UsersRow(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean UpdateUser(UsersRow usersRow)
	{
		try
		{
			String sql = "UPDATE 08692495_0000005.users" +
						" SET user_login='" + usersRow.user_login + "'"+
						", user_pass='" + usersRow.user_pass + "'"+
						", user_type='" + usersRow.user_type + "'"+
						", user_name='" + usersRow.user_name + "'"+
						", user_surname='" + usersRow.user_surname + "'"+
						", user_age='" + usersRow.user_age + "'"+
						" WHERE user_id='" + usersRow.user_id + "'";
			statement.executeUpdate(sql);
			return true;
		}
		catch(Exception e)
		{
			//System.out.println("Update operation failed...");
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	public boolean AddUser(UsersRow usersRow)
	{
		try
		{
			String sql = "INSERT INTO 08692495_0000005.users (user_login,user_pass,user_type,user_name,user_surname,user_age)" +
						" VALUES ('" + usersRow.user_login + "','" + usersRow.user_pass + "','" + usersRow.user_type + "','" + usersRow.user_name + "','" + usersRow.user_surname + "','" + usersRow.user_age + "')";
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Update operation failed...");
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	public boolean DeleteUser(int user_id)
	{
		try
		{
			String sql = "DELETE FROM 08692495_0000005.users" +
						" WHERE user_id='" + user_id + "'"; 
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	public static String[] UsersRowListToArray(ArrayList<UsersRow> list)
	{
		String[] tmpArray = new String[list.size()];
		for(int i = 0;i < list.size();i++)
		{
			tmpArray[i] = list.get(i).user_id + ".  " + list.get(i).user_name + "  " + list.get(i).user_surname; 
		}
		return tmpArray;
	}
	
	//movies
	public ArrayList<MoviesRow> GetMoviesList()
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.movies");
			int count = 0;
			MoviesRow tmp = new MoviesRow();
			ArrayList<MoviesRow> tmpList = new ArrayList<MoviesRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new MoviesRow(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getDouble(5),resultSet.getString(6),resultSet.getDouble(7),resultSet.getInt(8));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String[] MoviesRowListToArray(ArrayList<MoviesRow> list)
	{
		String[] tmpArray = new String[list.size()];
		for(int i = 0;i < list.size();i++)
		{
			tmpArray[i] = list.get(i).movie_id + ".  " + list.get(i).movie_title; 
		}
		return tmpArray;
	}
	public boolean UpdateMovie(MoviesRow moviesRow)
	{
		try
		{
			String sql = "UPDATE 08692495_0000005.movies" +
						" SET movie_title='" + moviesRow.movie_title + "'"+
						", movie_lenth='" + moviesRow.movie_lenth + "'"+
						", movie_min_age='" + moviesRow.movie_min_age + "'"+
						", movie_price='" + moviesRow.movie_price + "'"+
						", movie_genre='" + moviesRow.movie_genre + "'"+
						", movie_licence_price='" + moviesRow.movie_licence_price + "'"+
						", movie_licence_payment_day='" + moviesRow.movie_licence_payment_day + "'"+
						" WHERE movie_id='" + moviesRow.movie_id + "'";
			statement.executeUpdate(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	public boolean AddMovie(MoviesRow movieRow)
	{
		try
		{
			String sql = "INSERT INTO 08692495_0000005.movies (movie_title,movie_lenth,movie_min_age,movie_price,movie_genre,movie_licence_price,movie_licence_payment_day)" +
						" VALUES ('" + movieRow.movie_title + "','" + movieRow.movie_lenth + "','" + movieRow.movie_min_age + "','" + movieRow.movie_price + "','" + movieRow.movie_genre + "','" + movieRow.movie_licence_price + "','" + movieRow.movie_licence_payment_day + "')";
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	public boolean DeleteMovie(int movie_id)
	{
		try
		{
			String sql = "DELETE FROM 08692495_0000005.movies" +
						" WHERE movie_id='" + movie_id + "'"; 
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	//shows
	public ArrayList<ShowsRow> GetShowsList()
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.shows ORDER BY shows.show_date");
			int count = 0;
			ShowsRow tmp = new ShowsRow();
			
			ArrayList<ShowsRow> tmpList = new ArrayList<ShowsRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new ShowsRow(resultSet.getInt(1),resultSet.getInt(2),resultSet.getTimestamp(3));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ShowsRow> GetShowsList(int movie_id)
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.shows WHERE shows.show_movie_id='" + movie_id + "' ORDER BY shows.show_date");
			int count = 0;
			ShowsRow tmp = new ShowsRow();
			
			ArrayList<ShowsRow> tmpList = new ArrayList<ShowsRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new ShowsRow(resultSet.getInt(1),resultSet.getInt(2),resultSet.getTimestamp(3));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[] ShowsRowListToArray(ArrayList<ShowsRow> list)
	{
		String[] tmpArray = new String[list.size()];
		for(int i = 0;i < list.size();i++)
		{
			tmpArray[i] = list.get(i).show_date.toString(); 
		}
		return tmpArray;
	}
	public boolean AddShow(ShowsRow showRow)
	{
		try
		{
			String sql = "INSERT INTO 08692495_0000005.shows (show_movie_id,show_date)" +
						" VALUES ('" + showRow.show_movie_id + "','" + showRow.show_date + "')";
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	public boolean UpdateShow(ShowsRow showsRow)
	{
		try
		{
			int tmpLenth = showsRow.show_date.toString().length();
			String tmp = showsRow.show_date.toString().substring(0, tmpLenth-2);
			String sql = "UPDATE 08692495_0000005.shows" +
						" SET show_date='" + showsRow.show_date + "'"+
						" WHERE show_id='" + showsRow.show_id + "'";
			statement.executeUpdate(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	public boolean DeleteShow(int show_id)
	{
		try
		{
			String sql = "DELETE FROM 08692495_0000005.shows" +
						" WHERE show_id='" + show_id + "'"; 
			statement.execute(sql);
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	//tickets
	public ArrayList<TicketsRow> GetTicketsList(int show_id)
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.tickets WHERE tickets.ticket_show_id='" + show_id + "' ORDER BY tickets.ticket_seat_number");
			int count = 0;
			TicketsRow tmp = new TicketsRow();
			
			ArrayList<TicketsRow> tmpList = new ArrayList<TicketsRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new TicketsRow(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<TicketsRow> GetTicketsList()
	{
		try 
		{
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 08692495_0000005.tickets ORDER BY tickets.ticket_seat_number");
			int count = 0;
			TicketsRow tmp = new TicketsRow();
			
			ArrayList<TicketsRow> tmpList = new ArrayList<TicketsRow>();
			while(resultSet.next())
			{
				count++;
				tmp = new TicketsRow(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6));
				tmpList.add(tmp);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String[] TicketsRowListToArray(ArrayList<TicketsRow> list)
	{
		String[] tmpArray = new String[list.size()];
		for(int i = 0;i < list.size();i++)
		{
			tmpArray[i] = "User: " + list.get(i).ticket_user_id + " seat nr: " + list.get(i).ticket_seat_number + " ticket state: " + list.get(i).ticket_state; 
		}
		return tmpArray;
	}
}
