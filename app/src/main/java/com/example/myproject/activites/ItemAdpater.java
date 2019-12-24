package com.example.myproject.activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Item;

import java.util.ArrayList;

public class ItemAdpater extends BaseAdapter {
    private final ArrayList<Item> items;
    private final Context mContext;

    public ItemAdpater(ArrayList<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Item item = items.get(position);

        //if grid view recycled a cell re-instantiate it
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_item, null);
        }

        ImageView itemImage = (ImageView)convertView.findViewById(R.id.itemImage);
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);

        Glide.with(mContext).load(item.getItemImage()).into(itemImage);
        itemName.setText(item.getItemName());
        itemPrice.setText(item.getPrice() + "L.E.");

        return convertView;
    }
}
