package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.newfeds.walletheaven.helperclass.DbHelper;
import com.newfeds.walletheaven.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);

        int num = dbHelper.checkMasterPasswordCount();
        if(num > 0){
            Intent intent = new Intent(getBaseContext(), Dashboard.class);
            startActivity(intent);
        }else{
            Intent intent  = new Intent(getBaseContext(), NewMasterPassword.class);
            startActivity(intent);
        }




    }
}
