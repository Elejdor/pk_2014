package mainPackage;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ArraysEx {
	public static void FillTabsAndPrint(int size, int min, int max)
	{
		int tabRand[] = new int[size];
		Random randomGenerator = new Random();
		for (int i = 0; i < tabRand.length; i++)
		{
			tabRand[i] = randomGenerator.nextInt(max-min + 1) + min;
		}
		
		
		IOStream.stdout.printf("Random values array: ");
		PrintIntArray(tabRand);		
		SortTest(tabRand);
		
		int tabEven[] = new int[size];
		for (int i = 0; i < tabEven.length; i++)
		{
			tabEven[i] = 2*i;
			
		}
		
		IOStream.stdout.printf("\nEven values array: ");
		PrintIntArray(tabEven);
	}
	
	public static void SortTest(int tab[])
	{
		int tab1[] = tab.clone();
		int tab2[] = tab.clone();
		
		Date start, stop;
		
		start = new Date();
		BubbleSort(tab1, false);
		stop = new Date();
		
		IOStream.stdout.printf("\nSorted random values array: ");
		PrintIntArray(tab1);
		IOStream.stdout.printf("\nCzas sortowania bubble sort: %d ms", stop.getTime() - start.getTime());
		
		start = new Date();
		Arrays.sort(tab2);
		stop = new Date();
		IOStream.stdout.printf("\nCzas sortowania Arrays.sort(int[] args) sort: %d ms", stop.getTime() - start.getTime());
		
	}
	
	public static void BubbleSort(int[] tab)
	{
		BubbleSort(tab, false);
	}
	
	public static void BubbleSort(int[] tab, Boolean reverse)
	{
		for (int i = 0; i < tab.length; i++)
		{
			for (int j = 0; j < tab.length-i-1; j++)
			{
				if ((tab[j] > tab[j+1] && !reverse) || (tab[j] < tab[j+1] && reverse))
				{
					int swap = tab[j];
					tab[j] = tab[j+1];
					tab[j+1] = swap;
				}
			}
		}
		
	}
	
	public static void PrintIntArray(int arr[])
	{		
		for (int element : arr) {
			IOStream.stdout.printf(element + " ");
		}
	}
	
	
}
