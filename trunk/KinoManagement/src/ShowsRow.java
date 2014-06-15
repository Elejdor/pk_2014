import java.sql.*;

public class ShowsRow 
{
	public int show_id;
	public int show_movie_id;
	public Timestamp show_date;
	
	public ShowsRow()
	{
		
	}
	public ShowsRow(int show_id, int show_movie_id, Timestamp show_date)
	{
		this.show_id = show_id;
		this.show_movie_id = show_movie_id;
		this.show_date = show_date;
	}
}
