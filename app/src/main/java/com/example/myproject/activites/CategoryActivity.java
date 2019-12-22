package com.example.myproject.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myproject.R;
import com.example.myproject.model.Item;

public class CategoryActivity extends AppCompatActivity {
    private Item[] items;
    private final static String DEBUGMSG = "catdbg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        items = new Item[10];

        Intent intent = getIntent();
        if(intent.hasExtra("Category_ID")){
            int cat_id = intent.getIntExtra("Category_ID",0);
            for(int i = 0; i < items.length; i++)
                items[i] = new Item(0,"very cool item name",0,99.99);
        }
        //TODO change string according to nagy
        else if(intent.hasExtra("User_ID")){
            int user_id = intent.getIntExtra("User_ID",0);
            //TODO pass array from cloud
            for(int i = 0; i < items.length; i++)
                items[i] = new Item(0,"very cool item name",0,99.99);

        }
        else{
            for(int i = 0; i < items.length; i++)
                items[i] = new Item(0,"very cool item name",0,99.99);
        }

        ListView listView = (ListView) findViewById(R.id.itemListView);


        ItemAdpater itemAdpater = new ItemAdpater(items, this);
        listView.setAdapter(itemAdpater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = items[position];
                Log.d(DEBUGMSG, "item clicked");

                Intent intent1 = new Intent(getApplicationContext(), ItemViewActivity.class);
                intent1.putExtra("ITEM_ID", item.getItemID());
                startActivity(intent1);
            }
        });
    }
}
