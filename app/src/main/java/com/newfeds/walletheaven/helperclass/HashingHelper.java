package com.newfeds.walletheaven.helperclass;

/**
 * Created by Nonimux on 12/17/2015.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingHelper {

    public static String StringToSha256Hex(String toHash){

        String hexResult = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(toHash.getBytes());

            byte byteData[] = md.digest();

            hexResult = byteToHexString(byteData);


        } catch (NoSuchAlgorithmException e) {
            L.Log("Check alogrithm type! No such alogorithm found!");
        }
        return hexResult;
    }

    private static String byteToHexString(byte[] b){
        StringBuffer hexString = new StringBuffer();
        for(int i=0;i<b.length; i++){
            String hex = Integer.toHexString(0xff & b[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
