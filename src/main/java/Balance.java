import java.math.BigDecimal;
import com.blockcypher.exception.BlockCypherException;

public class Balance {
	
	
	/*
	 * returns the balance of the given address
	 */
	public static BigDecimal getAddressBalance(String s) throws BlockCypherException
	{	
		return TestnetUtil.context.getAddressService().getAddress(s).getBalance();
	}
	
	/*
	 * returns the final balance of the given address
	 */
	public static BigDecimal getAddressFinalBalance(String s) throws BlockCypherException
	{
		return TestnetUtil.context.getAddressService().getAddress(s).getFinalBalance();	
	}
	
}