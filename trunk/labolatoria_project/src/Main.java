/*
 * Zadanie osób:
 * £ukasz Nizik
 * Filip Wróbel
 * grupa 7
 */
import java.util.Arrays;

public class Main {

	/**
	 * @param args
	 */
	public static void wstawMandat(Mandat[] array)
	{
		array[0] = new Mandat("Kowalski", 300, 5, "123456");
		array[1] = new Mandat("Nowak", 150, 12, "123456");
		array[2] = new Mandat("Piechota", 123, 5, "123456");
		array[3] = new Mandat("Stengerdt", 2345, 30, "123456");
		array[4] = new Mandat("Kochanski", 213, 5, "123456");
		array[5] = new Mandat("Szczoch", 543, 12, "123456");
	}
	
	public static void pokazMandaty(Mandat[] array)
	{
		for (Mandat mandat : array) {
			System.out.println(mandat);
		}
	}
	
	public static void main(String[] args) {
		//utworzenie tablicy obiektów klasy Mandat o nazwie policjant
		Mandat[] policjant = new Mandat[6];
		
		//wype³nienie tabliby obiektami przy u¿yciu statycznej metody
		wstawMandat(policjant);
		
		//pokazanie nieposrotowanej tablicy
		pokazMandaty(policjant);
		
		//posortowanie tablicy mandatów
		Arrays.sort(policjant);
		
		
		//oddzielenie jednego wyswietlenia od drugiego
		System.out.println("----------------------------------");
		
		//pokazanie posrotowanej tablicy
		pokazMandaty(policjant);
		
		//utworzenie przyk³adowego obiektu typu Mandat
		Mandat przykladowyMandat = new Mandat("Janiak", 500,10, "123456");
		
		//konwersja  obiektu typu Mandat na obiekt typu Object
		Object nowyObiekt = przykladowyMandat;
		
		//konwersja typu Obiekt utworzonego z obiektu typu Mandat spowrotem na tym Mandat
		Mandat przywroconyMandat = (Mandat)nowyObiekt;
	}

}
