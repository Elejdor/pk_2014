package mainPackage;

public class ApplicationArgs {
	public static String DisplayArguments(String[] args)
	{
		String output = new String();
		
		for (String string : args) {
			output += string + "\n";
		}
		return output;
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
}
