package com.example.myproject.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.example.myproject.model.Category;
import com.example.myproject.utils.SharedCache;
import com.example.myproject.utils.SharedCache.*;

import static com.example.myproject.utils.SharedCache.accountItem;
import static com.example.myproject.utils.SharedCache.addItem;
import static com.example.myproject.utils.SharedCache.loginItem;
import static com.example.myproject.utils.SharedCache.logoutItem;

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


        for(int i = 0; i < categories.length; i++) categories[i] = new Category("Calculators",R.drawable.calculators);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories,this);
        gridView.setAdapter(categoryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Category category = categories[position];
                Log.d(DEBUGTAG, "listener working");
                //do an intent based on the category or the id
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                //replace by taking id from category array for good measures
//                i.putExtra("Category_ID", categories[position].id);
                i.putExtra("Category_ID", category.getName());
                startActivity(i);

            }
//            this function requests a refresh from the grid adapter to update an item in place
//            categoryAdapter.notifyDataSetChanged();
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        super.onCreateOptionsMenu(menu);
        SharedCache sharedCache = new SharedCache(this);
        loginItem = menu.findItem(R.id.loginIcon);
        logoutItem = menu.findItem(R.id.logoutIcon);
        accountItem = menu.findItem(R.id.myCoursesIcon);
        addItem=menu.findItem(R.id.addItemIcon);
        logoutItem.setVisible(true);
        accountItem.setVisible(true);
        addItem.setVisible(true);
        loginItem.setVisible(true);
        /*if()
        {
            logoutItem.setVisible(false);
            accountItem.setVisible(false);
            addItem.setVisible(false);
        }
        else
        {
            loginItem.setVisible(false);
        }*/
        return true;
    }
    public void addItem(MenuItem mi){
        Intent intent = new Intent(MainActivity.this , AddItemActivity.class);
        startActivity(intent);
    }

    public void onLogin(MenuItem mi)
    {
        Intent intent = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    public void onLogout(MenuItem mi)
    {

    }
    public void showProfile(){
        Intent intent = new Intent(MainActivity.this , ProfileActivity.class);
        startActivity(intent);
    }

}
