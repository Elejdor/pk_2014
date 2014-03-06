package mainPackage;

public class Arrays {
	public static void FillRandomAndPrint()
	{
		int size = 15;
		int tab[] = new int[size];
		
		for (int i = 0; i < tab.length; i++)
		{
			tab[i] = (int)(Math.random()*30);
		}
		
		PrintIntArray(tab);
		
		
	}
	
	public static void PrintIntArray(int arr[])
	{		
		for (int element : arr) {
			IOStream.stdout.printf(element + " ");
		}
	}
}
