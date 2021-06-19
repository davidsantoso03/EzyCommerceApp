package com.hfad.ezycommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Productdetail extends AppCompatActivity {
    TextView name, author,description;
    private Button btn_buy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        btn_buy= findViewById(R.id.buybtn);
        name = findViewById(R.id.name);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);


        String img = getIntent().getStringExtra("intent_img");
        String Productname = getIntent().getStringExtra("intent_name");
        String Productauthor = getIntent().getStringExtra("intent_author");
        String Productdescription = getIntent().getStringExtra("intent_desc");


        name.setText(Productname);
        author.setText(Productauthor);
        description.setText(Productdescription);


            btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCheckout();
                }
            });

        Picasso.get()
                .load( img)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into( (ImageView)findViewById(R.id.imageView) );

    }

    public void openCheckout(){
        Intent intent = new Intent(this,Checkout.class);
        startActivity(intent);
    }
}