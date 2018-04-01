import java.util.ArrayList;
import java.util.Arrays;
import com.blockcypher.exception.BlockCypherException;
import com.blockcypher.model.transaction.Transaction;
import com.blockcypher.model.transaction.intermediary.IntermediaryTransaction;
import com.blockcypher.utils.sign.SignUtils;

public class Payment
{
	/*
	 * makes a payment of a given satoshis from one address to another 
	 */
	public static Transaction makePayment(String input, String output, long satoshis, String privateKey) throws BlockCypherException
	{
		
		IntermediaryTransaction unsignedTx = TestnetUtil.context.getTransactionService()
				.newTransaction(new ArrayList<String>(Arrays.asList(input)),
								new ArrayList<String>(Arrays.asList(output)),
								satoshis);
		
		SignUtils.signWithBase58KeyWithPubKey(unsignedTx, privateKey);

		Transaction tx = TestnetUtil.context.getTransactionService().sendTransaction(unsignedTx);
		return tx;
	}

	
	

}
