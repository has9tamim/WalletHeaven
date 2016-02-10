package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.newfeds.walletheaven.R;

public class Credits extends AppCompatActivity {

    Button buttonVisitCreditSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        buttonVisitCreditSite = (Button) findViewById(R.id.buttonVisitCreditSite);

        buttonVisitCreditSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://newfeds.com"));
                startActivity(intent);
            }
        });


    }
}
