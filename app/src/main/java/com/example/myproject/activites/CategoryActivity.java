package com.example.myproject.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.model.Item;
import com.example.myproject.services.ItemService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<Item> items;
    private final static String DEBUGMSG = "catdbg";
    ListView listView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        items =new ArrayList<>();

        Intent intent = getIntent();
        if(intent.hasExtra("Category_ID")){
            String cat_name = intent.getStringExtra("Category_ID");
            getItemsByCat(cat_name);
        }
        else if(intent.hasExtra("User_ID")){
            int user_id = intent.getIntExtra("User_ID",0);
            getItemsByUserID(user_id);
        }
        listView = (ListView) findViewById(R.id.itemListView);
        progressBar = findViewById(R.id.progress_bar);
    }
    public void getItemsByUserID(final int id) {
        items = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Item item = postSnapshot.getValue(Item.class);
                    if (item.getOwnerID() == id) {
                        items.add(item);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    setAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CategoryActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getItemsByCat(final String cat){
        DatabaseReference databaseReference;
        items=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Item item = postSnapshot.getValue(Item.class);
                    if(item.getCategory().equals(cat)) {
                        items.add(item);
                    }
                    setAdapter();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CategoryActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void setAdapter() {
        if (items.size() != 0) {
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
        }else{
            Toast.makeText(CategoryActivity.this,"No Items in this Category",Toast.LENGTH_LONG).show();
        }
    }
}
