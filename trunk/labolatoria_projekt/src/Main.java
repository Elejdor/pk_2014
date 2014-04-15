/*
 * Zadanie osób:
 * £ukasz Nizik
 * Filip Wróbel
 * grupa 7
 */

public class Main 
{	
	public static void main(String[] args)
	{
		//utworzenie tablicy obiektów klasy Mandat o nazwie policjant
		RejestrMandatow mandaty = new RejestrMandatow();
		
		//wype³nienie tabliby obiektami
		mandaty.AddMandat(new Mandat("Kowalski", 300, 5, "123456"));
		mandaty.AddMandat(new Mandat("Nowak", 150, 12, "123456"));
		mandaty.AddMandat(new Mandat("Piechota", 123, 5, "123456"));
		mandaty.AddMandat(new Mandat("Stengerdt", 2345, 30, "123456"));
		mandaty.AddMandat(new Mandat("Kochanski", 213, 5, "123456"));
		mandaty.AddMandat(new Mandat("Szczoch", 543, 12, "123456"));		
		
		//pokazanie nieposrotowanej tablicy
		System.out.printf(mandaty.toString());
		
		//posortowanie tablicy mandatów
		mandaty.Sort();
				
		//oddzielenie jednego wyswietlenia od drugiego
		System.out.println("----------------------------------");
		
		//pokazanie posrotowanej tablicy
		System.out.printf(mandaty.toString());
		
		//zapis obiektu mandaty, typu RejestrMandatow do pliku .XML
		mandaty.toXML("wtorek.xml");
		
		//nowy obiekt typu RejestrMandatow
		RejestrMandatow rejestrOdzyskany = new RejestrMandatow();
		
		//wczytywanie rejestru z pliku .xml do obiektu rejestr odzyskany
		rejestrOdzyskany.fromXML("wtorek.xml");
		
		//oddzielenie drugiego wyswietlenia od trzeciego
		System.out.println("----------------------");
		
		//wyswietlenie odczytanego rejestru
		System.out.println(rejestrOdzyskany.toString());
	}

	
}