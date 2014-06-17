import java.sql.Timestamp;


public class Show 
{
	public String movieTitle;
	public Timestamp date;
	public double price;
	public Show(){}
	public Show(String movieTitle,Timestamp date, double price)
	{
		this.movieTitle = movieTitle;
		this.date = date;
		this.price = price;
	}
}
