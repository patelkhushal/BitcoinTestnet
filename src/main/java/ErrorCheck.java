import java.util.Scanner;

public class ErrorCheck {
	
	/*
	 * checks if user choice is "0", "1" or "2". If not ask user to input their choice again
	 */
	public static String getValidInput(Scanner s)
	{
		String choice = s.nextLine();
		while(!choice.equals("0") && !choice.equals("1") && !choice.equals("2"))
		{
			System.out.println("Please enter a valid number [0, 1 or 2]");
			Menu.displayMenu();
			choice = s.nextLine();
		}
		return choice;	
	}
	
	/*
	 * Takes a valid long input from the user
	 */
	public static long getValidLong(Scanner input)
	{
		String s;
		long validLong = 0;
		boolean check = false;
		while(!check)
		{
			s = input.nextLine();
			try{
				check = true;
				validLong = Long.parseLong(s);
			}
			catch (Exception e)
			{
				System.out.println("Please enter a valid amount:");
				check = false;
			}
		}
		return validLong;	
	}
	
}
