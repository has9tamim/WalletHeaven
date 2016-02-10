package com.newfeds.walletheaven.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.core.WalletStructure;
import com.newfeds.walletheaven.core.adapters.WalletListAdapter;

import java.util.ArrayList;

public class WalletList extends AppCompatActivity {

    ListView listViewWalletList;

    String[] walletTitle = {"Website Credentials", "Email Credentials"};
    int[] walletDrawId = {R.drawable.web, R.drawable.mail};
    String [] walletKey = {"websites","emails"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);

        final ArrayList<WalletStructure> walletStructures = new ArrayList<>();

        for(int i=0; i<walletTitle.length; i++){
            String wTitle = walletTitle[i];
            int wDrawId = walletDrawId[i];
            String wKey = walletKey[i];

            walletStructures.add(new WalletStructure(wDrawId, wTitle, wKey));
        }


        listViewWalletList = (ListView) findViewById(R.id.listViewWalletList);
        final WalletListAdapter walletListAdapter = new WalletListAdapter(getBaseContext(),walletStructures);

        listViewWalletList.setAdapter(walletListAdapter);

        listViewWalletList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = walletStructures.get(position).getKey();

                if(key.equals("websites")){
                    Intent intent = new Intent(getBaseContext(), WeblistActivity.class);
                    startActivity(intent);
                }

            }
        });




    }
}
