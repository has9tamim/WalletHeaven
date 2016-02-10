package com.newfeds.walletheaven.helperclass;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nonimux on 12/17/2015.
 */
public class L {
    private static final String debug_key = "com.debug.key";

    public static void Log(String s){
        Log.d(debug_key, s);
    }

    public static void Toast(Context c,String s){
        Toast.makeText(c,s, Toast.LENGTH_LONG).show();
    }
}
