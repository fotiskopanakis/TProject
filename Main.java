import java.io.File;

public class Main 
{
	public static void main(String[] args) 
	{
		if (args.length == 2) 
		{
			File file = new File(args[0]);
			File output = new File(args[1]);
		
			System.out.println("Finished processing.");
		}
		else
		{
			System.out.println("You must insert correct data.");
			System.exit(0);
		}
	}
}
