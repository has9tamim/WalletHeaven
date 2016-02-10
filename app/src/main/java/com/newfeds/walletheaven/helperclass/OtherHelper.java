package com.newfeds.walletheaven.helperclass;

/**
 * Created by Nonimux on 12/17/2015.
 */
public class OtherHelper {
    public static String mySubString(String myString, int start, int length) {
        return myString.substring(start, Math.min(start + length, myString.length()));
    }
}
