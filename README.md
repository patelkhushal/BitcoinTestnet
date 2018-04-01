# BitcoinTestnet
A java app that uses blockcypher's API to check the balance of a specific bitcoin testnet address and also make a payment using testnet bitcoins

## Setup for Eclipse IDE

**Step 1:** Download this repostiory. Unzip it on your local machine

**Step 2:** 
* Open Ecplise. 
* Go to File -> Import
* In Import wizard, select Maven -> Existing Maven Projects
* In root directory, browse to find this repository's local copy that you downloaded in Step 1 and click next
* Select pom.xml in Projects section and click Finish

**Step 3:** You might find errors in some packages saying "The method must override a superclass method". To resolve this issue, 
* Go to your Project properties -> Java compiler
* Unselect “use compliance from execution environment…” then Select compiler compliance level to 1.6
* Apply and let the eclipse rebuild the project for you.

**Step 4:** Finally, run the class called TestnetUtil in default package to see all the perks of this app!


## Code Explanation
* Using blockcypher's java sdk, we can interact with blockchain network. A class called BlockCypherContext in SDK provides us with services like address service, transaction service, info service, webhook service and block chain service. Since this app focuses on getting balance from a specific address and make a payment from one address to another, we can use address and transaction service of block cypher context.

* Since this is an user interactive app, I have classes like Menu and ErrorCheck to follow software design principle called seperation of concerns which means keeping the classes sepearte for different concerns. For example, Menu class only focuses on the main menu of the app and ErrorCheck only focuses on error checking the user input

### So, how do I get balance of a testnet address? 

* In TestnetUtil class there is a global static variable context of class BlockCypherContext
```java
public static BlockCypherContext context = new BlockCypherContext("v1", "btc", "test3", "f7e4206e12434e7491c674d44f6940db");
```
* This context object provides me with address and transaction services. I use this context object in Balance.java to return the address of a specified bitcoin testnet address.
```java
	/*
	 * returns the balance of the given address
	 */
	public static BigDecimal getAddressBalance(String s) throws BlockCypherException
	{	
		return TestnetUtil.context.getAddressService().getAddress(s).getBalance();
	}
```
* As you see address service already has a method ```.getAddress(s)``` that gets the address stored in some string s and returns an address objec. After getting the address object we can simply call ```.getBalance()``` to get its balance.

* If it is an invalid address then my program returns
```
Cannot get Balance. Invalid bitcoin testnet address!
```
### How does payment works?

* In order to make a payment, you need input and output addresses, amount you want to pay and private key of your bitcoin address. To make a payment, we use context's transaction services. We initilaize an Intermediary Transaction with the input and output addresses along with the amount in satoshis by using transaction service.
```java
IntermediaryTransaction unsignedTx = TestnetUtil.context.getTransactionService()
				.newTransaction(new ArrayList<String>(Arrays.asList(input)),
								new ArrayList<String>(Arrays.asList(output)),
								satoshis);
```
* To finalize this intermediary transaction we need to sign it using it's private key. We can do that by using blockcypher API's SignUtils class.
```java
SignUtils.signWithBase58KeyWithPubKey(unsignedTx, privateKey);
```
* And finally to send this complete transaction to blockchain, we can use sendTransaction method
```java
TestnetUtil.context.getTransactionService().sendTransaction(unsignedTx);
``` 
* If user provided invalid addresses, private key or if there isn't sufficient amount of funds in the input address, program outputs
```Cannot complete transaction. Invalid address(es)/key or not enough funds in withdraw address!```

## Appendix

* I generated bitcoin testnet address from here:

<https://www.bitaddress.org/bitaddress.org-v3.3.0-SHA256-dec17c07685e1870960903d8f58090475b25af946fe95a734f88408cef4aa194.html?testnet=true>

* I added funds to my bitcoin testnet using this bitcoin testnet faucet site:

<https://testnet.manu.backend.hamburg/faucet>

* If you want to check your bitcoin testnet's balance you can use this site or my program!

<https://live.blockcypher.com/>

* You can find Java sdk for BlockCypher here to interact with BlockCypher:

<https://github.com/blockcypher/java-client>
