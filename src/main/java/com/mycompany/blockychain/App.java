package com.mycompany.blockychain;
import com.mycompany.blockychain.Models.Block;

/**
 * Entry point of the application.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Round 1 of testing
        Block genesis = new Block("Hi, I'm the first block!", "0");
        System.out.println("Hash for Block 1: " + genesis.hash);

        Block second = new Block("Hi, I'm the second block!", genesis.hash);
        System.out.println("Hash for Block 2: " + second.hash);

        Block third = new Block("Hi, I'm the third block!", second.hash);
        System.out.println("Hash for Block 3: " + third.hash);
    }
}
