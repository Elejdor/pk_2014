package mainPackage;

import javax.swing.JOptionPane;

public class MainClass {

	public static void main(String[] args) {
		//IOStream.stdout.println(ApplicationArgs.DoOparation(args));
		
		int size = Integer.parseInt(JOptionPane.showInputDialog("Podaj rozmiar tablic"));
		ArraysEx.FillTabsAndPrint(size, 20, 50);
	}
}
