package com.mycompany.blockychain.Models;
import com.mycompany.blockychain.Helpers.*;
import java.util.Date;
/**
 * Class to model a block, where each block in the blockchain has its own hash signature.
 */
public class Block {
    public String hash;
    public String previousBlockHash;
    private String data;
    private long timeStamp;
    private int nonce;
    
    /**
     * Basic constructor of a block.
     * @param data - block data, textually represented.
     * @param previousBlockHash - the previous block's data, used in 
     * computing the current block's hash.
     */
    public Block(String data, String previousBlockHash) {
        this.data = data;
        this.previousBlockHash = previousBlockHash;
        this.timeStamp = new Date().getTime();
        this.hash = computeHash();
    }
    
    /**
     * Compute the current block's digital signature using the previous block's hash,
     * a timestamp, a nonce value and some textual data.
     * @return current block's hash.
     */
    public String computeHash() {
        String hash = StringUtil.sha256(
            previousBlockHash +
            Long.toString(timeStamp) +
            Integer.toString(nonce) +
            data
        );
        return hash;
    }
    
    /**
     * Mining is done using a proof-of-work approach based on the starting substring.
     * It requires a number of 0s based on difficulty to force miners to attempt
     * hashing at multiple nonces to combat tampering as a crypto network accepts
     * the longest valid chain and it is possible that an attacker would attempt 
     * to tamper an old block to generate newer blocks and a longer blockchain. 
     * This would allow the attacker to be rewarded by the network. Using the nonce 
     * approach makes the act of hashing more difficult and ensures that the number
     * of 'coins' awarded for new hash blocks does not increase linearly with the increase
     * in network computation power as new users start mining.
     * @param difficulty - sets the amount of 0s required.
     */
    public void mineBlock(int difficulty) {
    	// Set target substring made of difficulty * 0s.
    	String target = new String(new char[difficulty]).replace('\0', '0');
    	
    	// Increase the nonce factor until the desirable hash is computed.
    	while(!hash.substring(0, difficulty).equals(target)) {
    		nonce++;
    		hash = computeHash();
    	}
    	
    	System.out.println("Mined block with hash: " + hash);
    }
}