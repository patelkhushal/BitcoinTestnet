# BitcoinTestnet
A java app that uses blockcypher's API to check the balance of a specific bitcoin testnet address and also make a payment using testnet bitcoins

### Setup for Eclipse IDE

Step 1: Download this repostiory

Step 2: Open Ecplise. Go to File -> Import. In Import wizard, select Maven -> Existing Maven Projects. In root directory, browse to find this repository's local copy that you downloaded in Step 1. Select pom.xml Projects section and click Finish

Step 3: You might find errors in some packages saying "The method must override a superclass method". To resolve this issue, Go to your Project properties -> Java compiler -> Unselect “use compliance from execution environment…” then Select compiler compliance level to 1.6-> Apply and let the eclipse rebuild the project for you.

Step 4: Finally, run the class called TestnetUtil to see all the perks of this app!
