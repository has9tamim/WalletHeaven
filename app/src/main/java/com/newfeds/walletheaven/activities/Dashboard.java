package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newfeds.walletheaven.core.Keyholder;
import com.newfeds.walletheaven.R;

public class Dashboard extends AppCompatActivity {

    ImageButton imageButtonGoToWallet;
    ImageButton imageButtonLockStatus;
    ImageButton imageButtonSettings;
    ImageButton imageButtonCredits;

    @Override
    protected void onResume() {
        super.onResume();
        if(Keyholder.masterkey == null){
            imageButtonLockStatus.setImageResource(R.drawable.lockicon);
        }else{
            imageButtonLockStatus.setImageResource(R.drawable.unlockicon);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        imageButtonGoToWallet = (ImageButton) findViewById(R.id.imageButtonGoToWallet);
        imageButtonLockStatus = (ImageButton) findViewById(R.id.imageButtonLockStatus);
        imageButtonSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        imageButtonCredits = (ImageButton) findViewById(R.id.imageButtonCredits);

        if(Keyholder.masterkey == null){
            imageButtonLockStatus.setImageResource(R.drawable.lockicon);
        }else{
            imageButtonLockStatus.setImageResource(R.drawable.unlockicon);
        }




        imageButtonGoToWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Keyholder.masterkey == null){
                    Intent intent = new Intent(getBaseContext(), VerifyMasterPassword.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getBaseContext(), WalletList.class);
                    startActivity(intent);
                }
            }
        });

        imageButtonLockStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Keyholder.masterkey == null){
                    Toast.makeText(getBaseContext(), "Your credentials are locked and safe.", Toast.LENGTH_SHORT).show();
                }else{
                    Keyholder.masterkey = null;
                    imageButtonLockStatus.setImageResource(R.drawable.lockicon);
                    Toast.makeText(getBaseContext(), "Your credentials are locked.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Settings.class);
                startActivity(intent);
            }
        });

        imageButtonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Credits.class);
                startActivity(intent);
            }
        });



    }
}
