import java.util.Scanner;
import com.blockcypher.context.BlockCypherContext;
import com.blockcypher.exception.BlockCypherException;


public class TestnetUtil 
{
	public static BlockCypherContext context = new BlockCypherContext("v1", "btc", "test3", "f7e4206e12434e7491c674d44f6940db"); //only one BlockCypherContext object to use among all classes
	
	public static void main (String[] args) throws BlockCypherException
	{
		
		System.out.print("\nWhat would you like to do today? ");
		Menu.displayMenu();	
		Scanner userInput= new Scanner(System.in);
		String choice = ErrorCheck.getValidInput(userInput); //makes sure choice is either "0", "1" or "2"
	
		while(!choice.equals("0"))
		{
			if(choice.equals("1"))
			{
				
				System.out.println("Enter your bitcoin testnet address: ");
				String address = userInput.nextLine();
				try
				{
					System.out.println("Address: " + address);
					System.out.println("Balance: " + Balance.getAddressBalance(address) + " satoshis");
					System.out.println("Final Balance: " + Balance.getAddressFinalBalance(address) + " satoshis\n");
				}
				catch (Exception e)
				{
					System.out.println("Cannot get Balance. Invalid bitcoin testnet address!\n");
				}
			}
			
			else // choice is "2"
			{
				String inputAddress;
				String outputAddress;
				String privateKey;
				long amount = 0; 
				
				System.out.println("Enter the address of the bitcoin testnet you want to withdraw from:");
				inputAddress = userInput.nextLine();
				
				System.out.println("Enter your private key for this address:");
				privateKey = userInput.nextLine();
				
				System.out.println("Enter the address of the bitcoin testnet you want to deposit in:");
				outputAddress = userInput.nextLine();
				
				System.out.println("Enter the amount in satoshis you want to withdraw:");
				amount = ErrorCheck.getValidLong(userInput); //continue only if user inputs a long. If not then ask again for input
				
				try 
				{
					Payment.makePayment(inputAddress, outputAddress, amount, privateKey);
					System.out.println("Success!");
					System.out.println(amount + " satoshis withdrawn from address \"" + inputAddress + "\"");
					System.out.println("and deposited to address \"" + outputAddress + "\"" + "\n");
					
				} catch (Exception e) 
				{
					System.out.println("\nCannot complete transaction. Invalid address(es)/key or not enough funds in withdraw address!\n");
				}	
			}
			
			Menu.displayMenu();
			choice = ErrorCheck.getValidInput(userInput);
		}
		System.out.println("Thank you for using our service");
		userInput.close();
		
	}

}
