package com.mycompany.blockychain;
import com.mycompany.blockychain.Models.Block;
import com.google.gson.*;
import java.util.ArrayList;

/**
 * Entry point of the application.
 * Test is done using 5 blocks at a difficulty of 6.
 * As the difficulty increases, the computational power required
 * to generate the hashes is observed to increase, as expected.
 */
public class App 
{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    // Mining difficulty for proof of work.
    public static int difficulty = 5;
    
    public static void main(String[] args)
    {
        // Create a 5-block chain and output as Json.
        blockchain.add(new Block("This is the genesis block", "0"));
        System.out.println("Mining block 1 ...");
        blockchain.get(0).mineBlock(difficulty);
        
        blockchain.add(new Block("This is the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2 ...");
        blockchain.get(1).mineBlock(difficulty);
        
        blockchain.add(new Block("This is the third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 3 ...");
        blockchain.get(2).mineBlock(difficulty);
        
        blockchain.add(new Block("This is the fourth block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 4 ...");
        blockchain.get(3).mineBlock(difficulty);
        
        blockchain.add(new Block("This is the fifth block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 5 ...");
        blockchain.get(4).mineBlock(difficulty);
        
        
        String blockJSON = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("==== BLOCKCHAIN ====");
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
