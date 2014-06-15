public class UsersRow 
{
	public int user_id;
	public String user_login;
	public String user_pass;
	public String user_type;
	public String user_name;
	public String user_surname;
	public int user_age;
	
	public UsersRow()
	{
		
	}
	public UsersRow(int user_id, String user_login, String user_pass, String user_type, String user_name, String user_surname, int user_age)
	{
		this.user_id = user_id;
		this.user_login = user_login;
		this. user_pass =  user_pass;
		this.user_type = user_type;
		this.user_name = user_name;
		this.user_surname = user_surname;
		this.user_age = user_age;
	}
}
