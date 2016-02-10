package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.helperclass.DbHelper;

public class ShowWebCredsActivity extends AppCompatActivity {

    EditText editTextShowWebCredUrl;
    EditText editTextShowWebCredUsername;
    EditText editTextShowWebCredPassword;

    Button buttonEditWebCreds;
    Button buttonDeleteWebCreds;

    boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_creds);


        Intent intent = getIntent();

        final String id = intent.getStringExtra(WeblistActivity.ID_KEY);
        final String url = intent.getStringExtra(WeblistActivity.URL_KEY);
        final String username = intent.getStringExtra(WeblistActivity.USERNAME_KEY);
        final String password = intent.getStringExtra(WeblistActivity.PASSWORD_KEY);

        editTextShowWebCredUrl = (EditText) findViewById(R.id.editTextShowWebCredUrl);
        editTextShowWebCredUsername = (EditText) findViewById(R.id.editTextShowWebCredUsername);
        editTextShowWebCredPassword = (EditText) findViewById(R.id.editTextShowWebCredPassword);

        editTextShowWebCredUrl.setText(url);
        editTextShowWebCredUsername.setText(username);
        editTextShowWebCredPassword.setText(password);

        buttonEditWebCreds = (Button) findViewById(R.id.buttonEditWebCred);
        buttonDeleteWebCreds = (Button) findViewById(R.id.buttonDeleteWebCred);

        final DbHelper dbHelper = new DbHelper(this);

        buttonEditWebCreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMode == false) {
                    editTextShowWebCredUrl.setEnabled(true);
                    editTextShowWebCredUsername.setEnabled(true);
                    editTextShowWebCredPassword.setEnabled(true);

                    editTextShowWebCredUrl.requestFocus();
                    buttonDeleteWebCreds.setText("Ok");
                    buttonEditWebCreds.setText("Cancel");
                    editMode = true;
                }else{
                    editTextShowWebCredUrl.setEnabled(false);
                    editTextShowWebCredUsername.setEnabled(false);
                    editTextShowWebCredPassword.setEnabled(false);

                    editTextShowWebCredUrl.setText(url);
                    editTextShowWebCredUsername.setText(username);
                    editTextShowWebCredPassword.setText(password);

                    buttonDeleteWebCreds.setText("Delete");
                    buttonEditWebCreds.setText("Edit");
                    editMode = false;
                }
            }
        });

        buttonDeleteWebCreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editMode ==false) {
                    dbHelper.deleteWebCredentials(id);
                    Intent intent = new Intent(getBaseContext(), WeblistActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    String newUrl = editTextShowWebCredUrl.getText().toString();
                    String newUserName = editTextShowWebCredUsername.getText().toString();
                    String newPassword = editTextShowWebCredPassword.getText().toString();

                    dbHelper.editWebCredentials(id,newUrl,newUserName, newPassword);

                    Intent intent = new Intent(getBaseContext(), WeblistActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}
