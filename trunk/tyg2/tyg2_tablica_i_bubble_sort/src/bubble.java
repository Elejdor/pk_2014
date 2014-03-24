import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Date;

public class bubble {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//pobieramy parametry tablicy
		String text1 = JOptionPane.showInputDialog("Podaj d³ugoœæ tablicy:");
		String text2 = JOptionPane.showInputDialog("Podaj doln¹ granicê liczb");
		String text3 = JOptionPane.showInputDialog("Podaj górn¹ granicê liczb");
		
		//rzutujemy pobrane wartoœci ze stringów na liczby
		Integer lenth = Integer.valueOf(text1);
		Double floor = Double.valueOf(text2);
		Double ceil = Double.valueOf(text3);
		
		//tworzymy i wype³niamy tablicê
		Double tab[] = new Double[lenth];
		for(int  i = 0; i < lenth;i++)
		{
			tab[i] = floor + (Math.random() * (ceil - floor));
			tab[i] = Math.ceil(tab[i]);
		}
		
		//skopiowanie utworzonej tablicy
		Double tab2[] = new Double[lenth];
		for(int i = 0; i < lenth; i ++) tab2[i] = tab[i];
		
		//wypisanie utworzonej tablicy
		System.out.println("Utworzona tablica:");
		for(int i = 0 ; i < lenth; i ++)
		{
			System.out.print(tab[i] + "  ");
		}
		
		//sortowanie bubble z pomiarem czasu
		Double tmp = 0.0; //pomocnicza zmienna
		Date bubble_before = new Date();
		for(int i = (lenth-1);i > 0;i--)
		{
			for(int j = 0;j < i ; j++)
			{
				if(tab[j] > tab[j+1])
				{
					tmp = tab[j];
					tab[j] = tab[j+1];
					tab[j+1] = tmp;
				}
			}
		}
		Date bubble_after = new Date();
		
		//sortowanie przy pomocy funkcji Arrays.sort() z pomiarem czasu
		Date arrays_before = new Date();
		Arrays.sort(tab2);
		Date arrays_after = new  Date();
		
		//wypisanie posortowanej tablicy
		System.out.println("\nPosortowana tablica:");
		for(int i = 0 ; i < lenth; i ++)
		{
			System.out.print(tab[i] + "  ");
		}
		
		//wypisanie wyników pomiaru czasu podczas sortowania
		System.out.println("\nCzas sortowania:");
		System.out.println("Bubble sort: " + (bubble_after.getTime() - bubble_before.getTime() + " ms"));
		System.out.println("Arrays sort: " + (arrays_after.getTime() - arrays_before.getTime() + " ms"));
	}

}
