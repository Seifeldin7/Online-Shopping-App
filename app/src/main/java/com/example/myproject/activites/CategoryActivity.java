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
import com.example.myproject.services.ItemService;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<Item> items;
    private final static String DEBUGMSG = "catdbg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        items =new ArrayList<>();

        Intent intent = getIntent();
        if(intent.hasExtra("Category_ID")){
            String cat_name = intent.getStringExtra("Category_ID");
            ItemService itemService = new ItemService();
            items = itemService.getItemsByCat(cat_name);
        }
        else if(intent.hasExtra("User_ID")){
            int user_id = intent.getIntExtra("User_ID",0);
            ItemService itemService = new ItemService();
            items = itemService.getItemsByUserID(user_id);
        }
        ListView listView = (ListView) findViewById(R.id.itemListView);


        ItemAdpater itemAdpater = new ItemAdpater(items, this);
        listView.setAdapter(itemAdpater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = items.get(position);
                Log.d(DEBUGMSG, "item clicked");

                Intent intent1 = new Intent(getApplicationContext(), ItemViewActivity.class);
                intent1.putExtra("ITEM", item);
                startActivity(intent1);
            }
        });
    }
}
