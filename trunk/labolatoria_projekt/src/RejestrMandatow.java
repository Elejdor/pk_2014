/*
 * Zadanie os�b:
 * �ukasz Nizik
 * Filip Wr�bel
 * grupa 7
 */

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

import com.thoughtworks.xstream.XStream;

public class RejestrMandatow
{
	private ArrayList<Mandat> mandaty = new ArrayList<Mandat>();
	private XStream xstream; 

	public RejestrMandatow()
	{
		this.xstream = new XStream();
	}
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
	
	//motoda toXML s�u�y do zapisu zawarto�ci rejestru do pliku
	public void toXML(String fileName)
	{
		String xml = new String();
		xml = xstream.toXML(mandaty);
		try{
			File file = new File(fileName);
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		}
		catch(IOException error)
		{
			System.out.println("Error trying writing to file");
		}
	}
	
	//metoda fromXML s�u�y do odczytu zawarto�ci rejestru i wprowadzenie go do obiektu
	public void fromXML(String fileName)
	{
		String xml = new String();
		try{
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = new String();
			while((line = reader.readLine()) != null)
			{
				xml += line + "\n";
			}
			reader.close();
		}
		catch(IOException error)
		{
			System.out.println("Error reading from file.");
		}
		mandaty = (ArrayList<Mandat>)xstream.fromXML(xml);
	}
}
