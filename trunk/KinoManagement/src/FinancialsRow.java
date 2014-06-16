import java.sql.Date;
import java.sql.Timestamp;

public class FinancialsRow 
{
	public int financials_id;
	public double financials_value;
	public Timestamp financials_date;
	public String financials_type;
	public String financials_description;
	
	public FinancialsRow()
	{
	}
	public FinancialsRow(int financials_id, double financials_value, Timestamp financials_date, String financials_type, String financials_description)
	{
		this.financials_id = financials_id;
		this.financials_value = financials_value;
		this.financials_date = financials_date;
		this.financials_type = financials_type;
		this.financials_description = financials_description;		
	}
}
