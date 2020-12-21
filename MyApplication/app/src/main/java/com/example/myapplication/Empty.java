package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Empty extends AppCompatActivity {
    /**
     * This class is nothing special, just displays a post confirmation screen with a button that navigates to the MenueActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);


        FloatingActionButton Done = (FloatingActionButton) findViewById(R.id.Back);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cart.selectedproducts = null;
                Intent startIntent = new Intent(getApplicationContext(), MenueActivity.class);
                startActivity(startIntent);

            }
        });

    }
}