
public class TicketsRow 
{
	public int ticket_id;
	public int ticket_show_id;
	public int ticket_user_id;
	public int ticket_seat_number;
	public String ticket_state;
	public String ticket_discount;
	public TicketsRow()
	{
		
	}
	public TicketsRow(int ticket_id,int ticket_show_id, int ticket_user_id, int ticket_seat_number, String ticket_state, String ticket_discount)
	{
		this.ticket_id = ticket_id;
		this.ticket_show_id = ticket_show_id;
		this.ticket_user_id = ticket_user_id;
		this.ticket_seat_number = ticket_seat_number;
		this.ticket_state = ticket_state;
		this.ticket_discount = ticket_discount;
	}
}
