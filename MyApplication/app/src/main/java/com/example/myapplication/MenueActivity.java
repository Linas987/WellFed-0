package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import HelpeClasses.Products;
import HelpeClasses.User;

public class MenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        final TextView TableView=(TextView) findViewById(R.id.TableView);


        //for testing and getting seperate values
        final ArrayList pnamelist = new ArrayList();
        for(int i=0;i<MainActivity.prekes.size();i++)
        {
            Products test= (Products) MainActivity.prekes.get(i);
            System.out.println(test.getName());
            pnamelist.add(test.getName());
        }
        final String pnl=pnamelist.toString();

        /*final ArrayList unamelist = new ArrayList();
        for(int i=0;i<MainActivity.naudotojai.size();i++)
        {
            User test= (User) MainActivity.naudotojai.get(i);
            System.out.println(test.getUsername());
            unamelist.add(test.getUsername());
        }
        final String unl=unamelist.toString();*/

        MenueActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TableView.setText(pnl);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton Back = (FloatingActionButton) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Log.d( "WellFed0","Succsess");
                Intent startIntent =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
    }
}