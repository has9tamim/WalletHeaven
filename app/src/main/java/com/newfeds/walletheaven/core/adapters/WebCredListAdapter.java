package com.newfeds.walletheaven.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.core.WebCredStructure;

import java.util.ArrayList;

/**
 * Created by GT on 12/26/2015.
 */
public class WebCredListAdapter extends ArrayAdapter<WebCredStructure> {


    public WebCredListAdapter(Context context, ArrayList<WebCredStructure> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WebCredStructure ws = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_web_cred_list, parent,false);
        }

        TextView textViewListWebCredUrl = (TextView) convertView.findViewById(R.id.textViewListWebCredUrl);
        TextView textViewListWebCredUsername = (TextView) convertView.findViewById(R.id.textViewListWebCredUsername);

        textViewListWebCredUrl.setText(ws.getUrl());
        textViewListWebCredUsername.setText(ws.getUsername());

        return convertView;
    }
}
