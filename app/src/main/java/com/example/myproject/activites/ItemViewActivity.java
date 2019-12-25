package com.example.myproject.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Item;

public class ItemViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,price, description, date;
    EditText mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Intent intent = getIntent();
        Item item =(Item)intent.getSerializableExtra("ITEM");

        imageView = findViewById(R.id.itemActivityImage);
        name = findViewById(R.id.itemActivityName);
        price = findViewById(R.id.itemActivityPrice);
        description = findViewById(R.id.itemActivityDescription);
        date = findViewById(R.id.itemActivityDate);
        mail= findViewById(R.id.itemActivityUserMail);

        Glide.with(this).load(item.getItemImage()).into(imageView);

        name.setText(item.getItemName());
        price.setText(item.getPrice() + "EGP");
        description.setText(item.getItemName());
        date.setText(item.getDate());

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mail.getText().toString();
                String hidden = getResources().getString(R.string.hidden_mail);
                if(value.equals(hidden)){
                    //TODO check for login before showing mail
                    mail.setText("coolpeople@coolapp.com");
                }
                else {
                    mail.setText(hidden);
                }
            }
        });
    }
}
