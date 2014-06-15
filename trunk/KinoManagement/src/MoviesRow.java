
public class MoviesRow 
{
	public int movie_id;
	public String movie_title;
	public int movie_lenth;
	public int movie_min_age;
	public double movie_price;
	public String movie_genre;
	public double movie_licence_price;
	public int movie_licence_payment_day;
	
	public MoviesRow()
	{
		
	}
	public MoviesRow(int movie_id, String movie_title, int movie_lenth,int movie_min_age, double movie_price, String movie_genre, double movie_licence_price, int movie_licence_payment_day)
	{
		this.movie_id = movie_id;
		this.movie_title = movie_title;
		this.movie_lenth = movie_lenth;
		this.movie_min_age = movie_min_age;
		this.movie_price = movie_price;
		this.movie_genre = movie_genre;
		this.movie_licence_price = movie_licence_price;
		this.movie_licence_payment_day = movie_licence_payment_day;
	}
}
