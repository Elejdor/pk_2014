package mainPackage;
import java.io.*;

public class MainClass {
	static PrintWriter stdout = new PrintWriter(System.out, true);
	
	public static void DisplayArguments(String[] args)
	{
		for (String string : args) {
			stdout.println(string);
		}
	}
	
	public static String DoOparation(String[] args)
	{
		String output = new String();
		if (args.length == 3)
		{
			if (args[1].length() != 1)
			{
				output = args[1].toString() + " is a wrong operation (you can use -, +, /, *)";
			}
			else
			{				
				int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[2]);
				char operation = args[1].charAt(0);
				double result= 0;
				switch (operation)
				{
				case '-':
					result = a - b;
					break;
				case '+':
					result = a + b;
					break;
				case '/':
					result = (double)a/b;
					break;
				case '*':
					result = a*b;
					break;
				}
				output = Double.toString(result);
			}
		}
		else
		{
			output = args.length + " is a wrong number of arguments, please use exactly 3";
		}
		return output;
	}
	
	public static void main(String[] args) {
		stdout.println(DoOparation(args));
	}
}
