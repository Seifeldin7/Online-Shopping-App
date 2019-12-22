package com.example.myproject.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.example.myproject.model.Item;

public class ItemViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,price, description;
    EditText mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ITEM_ID",0);
        Item item = new Item(0,"very cool item name",0,99.99);

        imageView = findViewById(R.id.itemActivityImage);
        name = findViewById(R.id.itemActivityName);
        price= findViewById(R.id.itemActivityPrice);
        description= findViewById(R.id.itemActivityDescription);
        mail= findViewById(R.id.itemActivityUserMail);

        //TODO change to actual image
        imageView.setImageResource(R.drawable.common_google_signin_btn_icon_dark);

        name.setText(item.getItemName());
        price.setText(item.getPrice() + "EGP");
        description.setText(item.getItemName());

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
