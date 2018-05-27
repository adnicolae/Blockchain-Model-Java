package com.mycompany.blockychain.Helpers;
import java.security.MessageDigest;

/**
 * Helper class that makes use of the SHA256 algorithm to provide a hashing method.
 */
public class StringUtil {
    public static String sha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Apply SHA-256 to the input string.
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            // Convert the hash to a hexadecimal string.
            StringBuffer hexString = new StringBuffer();
            for (byte h : hash) {
                // Mask the byte with 0xFF to undo the sign-extension from 8-bit byte
                // to a 32-bit integer.
                String hex = Integer.toHexString(0xFF & h);
                // Pad with zeroes.
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}