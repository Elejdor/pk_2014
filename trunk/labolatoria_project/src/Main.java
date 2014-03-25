/*
 * Zadanie osób:
 * £ukasz Nizik
 * Filip Wróbel
 * grupa 7
 */

public class Main {
	//metoda do testowania funkcjonalnosci rejestru
	public static void wstawMandat(RejestrMandatow rejestr)
	{
		rejestr.AddMandat(new Mandat("Kowalski", 300, 5, "123456"));
		rejestr.AddMandat(new Mandat("Nowak", 150, 12, "123456"));
		rejestr.AddMandat(new Mandat("Piechota", 123, 5, "123456"));
		rejestr.AddMandat(new Mandat("Stengerdt", 2345, 30, "123456"));
		rejestr.AddMandat(new Mandat("Kochanski", 213, 5, "123456"));
		rejestr.AddMandat(new Mandat("Szczoch", 543, 12, "123456"));
	}
	
	public static void main(String[] args) {
		//utworzenie tablicy obiektów klasy Mandat o nazwie policjant
		RejestrMandatow mandaty = new RejestrMandatow();
		
		//wype³nienie tabliby obiektami przy u¿yciu statycznej metody
		wstawMandat(mandaty);		
		
		//pokazanie nieposrotowanej tablicy
		System.out.printf(mandaty.toString());
		
		//posortowanie tablicy mandatów
		mandaty.Sort();
				
		//oddzielenie jednego wyswietlenia od drugiego
		System.out.println("----------------------------------");
		
		//pokazanie posrotowanej tablicy
		System.out.printf(mandaty.toString());
	}

	
}
