package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.core.WebCredStructure;
import com.newfeds.walletheaven.core.adapters.WebCredListAdapter;
import com.newfeds.walletheaven.helperclass.DbHelper;

import java.util.ArrayList;

public class WeblistActivity extends AppCompatActivity {

    ListView listViewWebCredList;

    public static final String ID_KEY = "com.bohemoninteractive.walletheaven.id";
    public static final String URL_KEY = "com.bohemoninteractive.walletheaven.url";
    public static final String USERNAME_KEY = "com.bohemoninteractive.walletheaven.username";
    public static final String PASSWORD_KEY = "com.bohemoninteractive.walletheave.password";
    ArrayList<WebCredStructure> webCredStructures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weblist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webCredStructures = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(this);
        webCredStructures = dbHelper.takeOutWebCredentials();

        listViewWebCredList = (ListView) findViewById(R.id.listViewWebCredList);

        WebCredListAdapter webCredListAdapter = new WebCredListAdapter(getBaseContext(), webCredStructures);

        listViewWebCredList.setAdapter(webCredListAdapter);
        listViewWebCredList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebCredStructure webCredStructure =  webCredStructures.get(position);
                Intent intent =  new Intent(getBaseContext(), ShowWebCredsActivity.class);

                intent.putExtra(ID_KEY, webCredStructure.getId().toString());
                intent.putExtra(URL_KEY, webCredStructure.getUrl());
                intent.putExtra(USERNAME_KEY,webCredStructure.getUsername());
                intent.putExtra(PASSWORD_KEY, webCredStructure.getPassword());

                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddWebCredentials);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddWebCredActivity.class);
                startActivity(intent);
            }
        });
    }



}
