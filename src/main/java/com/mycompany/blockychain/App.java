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
    }
}
