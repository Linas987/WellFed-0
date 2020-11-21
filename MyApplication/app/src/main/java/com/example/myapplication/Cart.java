package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        FloatingActionButton Buy = (FloatingActionButton) findViewById(R.id.Buy);
        Buy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent startIntent =new Intent(getApplicationContext(), Empty.class);
                startActivity(startIntent);

            }
        });

        FloatingActionButton Back = (FloatingActionButton) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent startIntent =new Intent(getApplicationContext(), MenueActivity.class);
                startActivity(startIntent);

            }
        });
    }
}