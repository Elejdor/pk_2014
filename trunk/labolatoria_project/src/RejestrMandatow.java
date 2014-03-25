/*
 * Zadanie osób:
 * £ukasz Nizik
 * Filip Wróbel
 * grupa 7
 */

import java.util.ArrayList;
import java.util.Collections;


public class RejestrMandatow {
	private ArrayList<Mandat> mandaty = new ArrayList<Mandat>();

	public ArrayList<Mandat> getMandaty() {
		return mandaty;
	}
	
	public void AddMandat(Mandat mandat)
	{
		mandaty.add(mandat);
	}
	
	public Mandat GetMandat(int n)
	{
		return mandaty.get(n);
	}
	
	public void SetMandat(int n, Mandat mandat)
	{
		mandaty.set(n, mandat);
	}
	
	public void Sort()
	{
		Collections.sort(mandaty);
	}
	
	public String toString()
	{
		String output = new String();
		for (Mandat mandat : this.getMandaty()) {
			output += mandat.toString() + "\n";
		}
		
		return output;
	}
}
