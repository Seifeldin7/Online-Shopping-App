package com.example.myproject.activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.model.Category;

public class CategoryAdapter extends BaseAdapter {
    //source https://www.raywenderlich.com/995-android-gridview-tutorial
    private final Category[] categories;
    private final Context mContext;

    public CategoryAdapter(Category[] categories, Context context) {
        this.categories = categories;
        this.mContext= context;
    }

    @Override
    public int getCount() {
        return categories.length;
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
        final Category category = categories[position];

        //if grid view recycled a cell re-instantiate it
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.category_item, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.categoryName);
        ImageView image = (ImageView) convertView.findViewById(R.id.categoryImage);

        //replace by actual category name from position
        text.setText(category.getName());
        image.setImageResource(category.getImage());

        return convertView;
    }
}
