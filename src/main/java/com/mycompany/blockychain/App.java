package com.mycompany.blockychain;
import com.mycompany.blockychain.Models.Block;
import com.google.gson.*;
import java.util.ArrayList;

/**
 * Entry point of the application.
 *
 */
public class App 
{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main( String[] args )
    {
        // Create a 3-block chain and output as Json
        blockchain.add(new Block("This is the genesis block", "0"));
        blockchain.add(new Block("This is the second block", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("This is the third block", blockchain.get(blockchain.size()-1).hash));

        String blockJSON = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockJSON);
        System.out.println(isChainValid() ? "Blockchain is valid." : "Blockchain is not valid.");
    }
    
    // Check blockchain integrity.
    public static Boolean isChainValid() {
    	Block current;
    	Block previous;
    	
    	for (int i = 1; i < blockchain.size(); i++) {
    		current = blockchain.get(i);
    		previous = blockchain.get(i-1);
    		
    		// Compare the registered hash and computed hash.
    		if (!current.hash.equals(current.computeHash())) {
    			System.out.println("Current hashes not equal.");
    			return false;
    		}
    		
    		// Compare previous hash in the chain with the registered previous hash.
    		if (!previous.hash.equals(current.previousBlockHash)) {
    			System.out.println("Previous hashes not equal.");
    			return false;
    		}
    	}
    	return true;
    }
}
