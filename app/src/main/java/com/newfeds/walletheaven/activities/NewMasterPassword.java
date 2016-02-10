package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.newfeds.walletheaven.helperclass.DbHelper;
import com.newfeds.walletheaven.helperclass.HashingHelper;
import com.newfeds.walletheaven.core.Keyholder;
import com.newfeds.walletheaven.helperclass.OtherHelper;
import com.newfeds.walletheaven.R;

public class NewMasterPassword extends AppCompatActivity {

    Button buttonDoneEnteringMasterPassword;
    EditText editTextEnterMasterPassword;
    EditText editTextEnterMasterPasswordCheck;
    TextView textViewMasterPasswordStrength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_master_password);

        buttonDoneEnteringMasterPassword = (Button) findViewById(R.id.buttonDoneEnteringMasterPassword);
        editTextEnterMasterPassword = (EditText) findViewById(R.id.editTextNewMasterPassword);
        editTextEnterMasterPasswordCheck = (EditText) findViewById(R.id.editTextNewMasterPasswordCheck);
        textViewMasterPasswordStrength = (TextView) findViewById(R.id.textViewPasswordStrength);

        editTextEnterMasterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editTextEnterMasterPassword.getText().toString();
                int p = text.length();
                if(p<6){
                    textViewMasterPasswordStrength.setText("At least six characters needed");
                }else{
                    textViewMasterPasswordStrength.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        buttonDoneEnteringMasterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plainPassword = editTextEnterMasterPassword.getText().toString();
                String plainTextChange = editTextEnterMasterPasswordCheck.getText().toString();
                if (plainPassword.length() < 6) {
                    textViewMasterPasswordStrength.setText("At least six characters needed");
                }else if(!plainPassword.equals(plainTextChange)){
                    editTextEnterMasterPasswordCheck.setError("Passwords don't match");
                }else {
                    textViewMasterPasswordStrength.setText("");
                    String hashedPassword = HashingHelper.StringToSha256Hex(plainPassword);

                    String leftHash = OtherHelper.mySubString(hashedPassword, 0, 32);
                    String rightHash = OtherHelper.mySubString(hashedPassword, 33, 32);

                    Keyholder.masterkey = leftHash;

                    DbHelper dbHelper = new DbHelper(getBaseContext());

                    dbHelper.InsertPassword(rightHash);

                    Intent intent = new Intent(getBaseContext(), Dashboard.class);
                    startActivity(intent);

                }
            }
        });





    }
}
