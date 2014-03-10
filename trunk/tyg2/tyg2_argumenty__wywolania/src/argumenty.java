
public class argumenty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 3)
		{
			System.out.println("Error: not enought arguments!");
		}else{
			if(args.length == 3)
			{
				System.out.println(args[0] + " * " + args[1] + " ** " + args[2] + " ***");
			}else{
				for(int i = 0; i < args.length;i++)
				{
					System.out.print("\n" + args[i] + " ");
					for(int j = 0;j < (i+1);j++) System.out.print("*");
				}
			}
			
		}
		

	}

}
