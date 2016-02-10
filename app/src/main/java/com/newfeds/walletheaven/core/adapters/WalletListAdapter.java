package com.newfeds.walletheaven.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newfeds.walletheaven.R;
import com.newfeds.walletheaven.core.WalletStructure;

import java.util.ArrayList;

/**
 * Created by GT on 12/26/2015.
 */
public class WalletListAdapter extends ArrayAdapter<WalletStructure> {

    public WalletListAdapter(Context context, ArrayList<WalletStructure> objects){
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WalletStructure ws = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_wallet_list,parent, false);
        }

        ImageView imageViewWalletList = (ImageView) convertView.findViewById(R.id.imageViewWalletList);
        TextView textViewWalletList = (TextView) convertView.findViewById(R.id.textViewWalletList);

        imageViewWalletList.setImageResource(ws.getDrawableId());
        textViewWalletList.setText(ws.getTitle());



        return convertView;
    }
}
