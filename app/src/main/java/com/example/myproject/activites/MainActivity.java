package com.example.myproject.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.example.myproject.model.Category;

public class MainActivity extends AppCompatActivity {
    private Category[] categories;
    String DEBUGTAG = "mainactd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView)findViewById(R.id.categoryGridView);

        //call a function to return categories array
        categories = new Category[10];


        for(int i = 0; i < categories.length; i++) categories[i] = new Category();
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories,this);
        gridView.setAdapter(categoryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Category category = categories[position];
                Log.d(DEBUGTAG, "listener working");
                //do an intent based on the category or the id

            }
//            this function requests a refresh from the grid adapter to update an item in place
//            categoryAdapter.notifyDataSetChanged();
        });


    }
}
