package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newfeds.walletheaven.core.Keyholder;
import com.newfeds.walletheaven.helperclass.DbHelper;
import com.newfeds.walletheaven.helperclass.HashingHelper;
import com.newfeds.walletheaven.helperclass.L;
import com.newfeds.walletheaven.helperclass.OtherHelper;
import com.newfeds.walletheaven.R;

public class VerifyMasterPassword extends AppCompatActivity {

    Button buttonVerifyMasterPassword;
    EditText editTextVerifyMasterPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_master_password);

        buttonVerifyMasterPassword = (Button) findViewById(R.id.buttonVerifyMasterPassword);
        editTextVerifyMasterPassword = (EditText) findViewById(R.id.editTextVerifyMasterPassword);

        buttonVerifyMasterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plainPass = editTextVerifyMasterPassword.getText().toString();
                String hashfull = HashingHelper.StringToSha256Hex(plainPass);

                String rightHash = OtherHelper.mySubString(hashfull, 33, 32);
                String leftHash = OtherHelper.mySubString(hashfull, 0, 32);

                DbHelper dbHelper = new DbHelper(getBaseContext());

                String dbRightHash = dbHelper.getMasterPasswordHalfHash();
                L.Log("From db: " + dbRightHash);
                L.Log("From io: "+rightHash);

                if(rightHash.equals(dbRightHash)){
                    Keyholder.masterkey = leftHash;
                    L.Log("masterkey: " + Keyholder.masterkey);
                    Intent intent = new Intent(getBaseContext(), WalletList.class);
                    startActivity(intent);
                }else{
                    editTextVerifyMasterPassword.setError("Password Is Wrong!");
                }
            }
        });


    }
}
