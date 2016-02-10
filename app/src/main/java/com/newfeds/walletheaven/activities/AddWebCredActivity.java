package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.helperclass.DbHelper;
import com.newfeds.walletheaven.helperclass.L;

public class AddWebCredActivity extends AppCompatActivity {

    EditText editTextAddWebCredUrl;
    EditText editTextAddWebCredUsername;
    EditText editTextAddWebCredPassword;

    Button buttonAddWebCredApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_web_cred);



        editTextAddWebCredUrl = (EditText) findViewById(R.id.editTextAddWebCredUrl);
        editTextAddWebCredUsername = (EditText) findViewById(R.id.editTextAddWebCredUsername);
        editTextAddWebCredPassword = (EditText) findViewById(R.id.editTextAddWebCredPassword);

        buttonAddWebCredApply = (Button) findViewById(R.id.buttonAddWebCredApply);

        final DbHelper dbHelper = new DbHelper(this);

        buttonAddWebCredApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUrl = editTextAddWebCredUrl.getText().toString();
                String inputUsername = editTextAddWebCredUsername.getText().toString();
                String inputPassword = editTextAddWebCredPassword.getText().toString();

                if((inputUrl.length()==0)|| (inputUsername.length()==0)||(inputPassword.length()==0)){
                    Toast.makeText(getBaseContext(),"You cant leave a field empty!", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.insertWebCredentials(inputUrl, inputUsername, inputPassword);
                    L.Log("inserted webcred");
                    Intent intent = new Intent(getBaseContext(), WeblistActivity.class);
                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}
