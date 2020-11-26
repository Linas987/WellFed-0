package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import HelpeClasses.User;

public class Cart extends AppCompatActivity {

    User user=MenueActivity.user;

    static ArrayList<String> selectedproducts;
    static String moreInfo;


    private String ConfertArrayListToFormatedString (ArrayList<String> aString)
    {
        String sp = "";
        for(int i=0;i<aString.size();i++) {
            //System.out.println(sp+selectedproducts.get(i)+"\n");

            sp=(sp+aString.get(i)+"\n");
        }
        return sp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView listofprod =(TextView) findViewById(R.id.SelectView);
        final EditText location=(EditText) findViewById(R.id.location);
        TextView calculations =(TextView) findViewById(R.id.TextInfo);

        listofprod.setText(ConfertArrayListToFormatedString(selectedproducts));
        System.out.println(moreInfo);
        calculations.setText(moreInfo);



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