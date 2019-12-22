package com.example.myproject.activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.model.Item;

public class ItemAdpater extends BaseAdapter {
    private final Item[] items;
    private final Context mContext;

    public ItemAdpater(Item[] items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return items.length;
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
        final Item item = items[position];

        //if grid view recycled a cell re-instantiate it
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_item, null);
        }

        ImageView itemImage = (ImageView)convertView.findViewById(R.id.itemImage);
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);

        itemName.setText(item.getItemName());
        itemPrice.setText(item.getPrice() + "L.E.");
        //TODO replace image with actual image
        itemImage.setImageResource(R.drawable.ic_launcher_foreground);

        return convertView;
    }
}
