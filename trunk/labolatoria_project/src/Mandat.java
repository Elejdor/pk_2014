/*
 * Zadanie osób:
 * £ukasz Nizik
 * Filip Wróbel
 * grupa 7
 */
import java.util.Comparator;
import java.util.Date;

class Mandat implements Comparable<Mandat>, Comparator<Mandat>, ISimpleData
{
	private String nazwiskoKierowcy;
	private double wysokoscMandatu;
	private int punktyKarne;
	private String pesel;

	private double wplaconoKwote = 0;
	
	private Date dataZdarzenia;
	
	public String toCSV()
	{		
		return nazwiskoKierowcy + ","
				+ wysokoscMandatu + ","
				+ punktyKarne + ","
				+ pesel + ","
				+ wplaconoKwote;
	}
	
	public void getCSV(String data)
	{
		String []tab = data.split("[,]");
		nazwiskoKierowcy = tab[0];
		wysokoscMandatu = Double.parseDouble(tab[1]);
		punktyKarne = Integer.parseInt(tab[2]);
		pesel = tab[3];
		wplaconoKwote = Double.parseDouble(tab[4]);
	}
	
	public Mandat(String nazwisko, int wysokoscMandatu, int punktyKarne, String pesel)
	{
		this.setNazwiskoKierowcy(nazwisko);
		this.setWysokoscMandatu(wysokoscMandatu);
		this.setPunktyKarne(punktyKarne);
		this.setPesel(pesel);
		this.setDataZdarzenia(new Date());
	}

	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public int getPunktyKarne() {
		return punktyKarne;
	}
	public void setPunktyKarne(int punktyKarne) {
		this.punktyKarne = punktyKarne;
	}


	public double getWysokoscMandatu() {
		return wysokoscMandatu;
	}
	public void setWysokoscMandatu(double wysokoscMandatu) {
		this.wysokoscMandatu = wysokoscMandatu;
	}


	public String getNazwiskoKierowcy() {
		return nazwiskoKierowcy;
	}
	private void setNazwiskoKierowcy(String nazwiskoKierowcy) {
		this.nazwiskoKierowcy = nazwiskoKierowcy;
	}

	public Date getDataZdarzenia() {
		return dataZdarzenia;
	}
	private void setDataZdarzenia(Date dataZdarzenia) {
		this.dataZdarzenia = dataZdarzenia;
	}
	
	public String toString()
	{
		String tmp = new String();
		tmp += 	"\nMandat na osobê: " + this.getNazwiskoKierowcy() + 
				"\n PESEL: " + this.getPesel() + 
				"\n wysokoœæ mandatu: " + this.getWysokoscMandatu() +
				"\n punkty karne: " + this.getPunktyKarne() + 
				"\n data zdarzenia: " + this.getDataZdarzenia();
		return tmp;
	}
	
	public int compareTo(Mandat tmp)
	{
		if(this.getDataZdarzenia().getTime() < tmp.getDataZdarzenia().getTime())
		{
			return -1;
		}else{
			if(this.getDataZdarzenia().getTime() == tmp.getDataZdarzenia().getTime())
			{
				return 0;
			}
			return 1;
		}
		
	}
	public int compare(Mandat arg0, Mandat arg1)
	{
		if(arg0.getDataZdarzenia().getTime() < arg1.getDataZdarzenia().getTime())
		{
			return -1;
		}else{
			if(arg0.getDataZdarzenia().getTime() == arg1.getDataZdarzenia().getTime())
			{
				return 0;
			}
			return 1;
		}
	}
	public boolean equals(Mandat arg0)
	{
		return (this.getPesel() == arg0.getPesel()) && (this.getDataZdarzenia() == arg0.getDataZdarzenia() ); 
	}
	
	//medota pokazuj¹ca mechanizm is()
	//w tym przypadku sprawdza, czy osoba która otrzyma³a mandat wp³aci³a pieni¹dze wystarczaj¹ce na mandat
	public boolean isZaplacone()
	{
		return this.getWplaconoKwote() > this.wysokoscMandatu;
	}

	public double getWplaconoKwote() {
		return wplaconoKwote;
	}
	public void wplacKwote(double wplaconoKwote) {
		this.wplaconoKwote += wplaconoKwote;
	}
}
