package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import HelpeClasses.Products;

public class MenueActivity extends AppCompatActivity {
    TableLayout stk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        final TableLayout TableL=(TableLayout)findViewById(R.id.TableL);

        //for testing and getting seperate values
        /*final ArrayList pnamelist = new ArrayList();
        for(int i=0;i<MainActivity.prekes.size();i++)
        {
            Products test= (Products) MainActivity.prekes.get(i);
            System.out.println(test.getName());
            pnamelist.add(test.getName());
        }
        final String pnl=pnamelist.toString();*/

        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0,j = stk.getChildCount(); i< j ;i++)
                {
                    try {
                        //get Child views of the table with table.getChildAt(int index), this returns a view
                        //cast it to a TextView
                        View vi = stk.getChildAt(i);
                        TableRow r = (TableRow) vi;
                        //in this row (row i) of the table get the child element(column) where the first column would have a value of 0
                        TextView getCatno = (TextView) r.getChildAt(3);
                        String CatNo = getCatno.getText().toString();
                        System.out.println(CatNo);
                        Intent startIntent =new Intent(getApplicationContext(), Cart.class);
                        startActivity(startIntent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

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
    public void init()
    {
        stk = (TableLayout) findViewById(R.id.TableL);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Product ");
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Price ");
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Calories ");
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" num ");
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" select ");
        tbrow0.addView(tv4);
        stk.addView(tbrow0);
        for (int i=0;i<MainActivity.prekes.size();i++) {
            Products test= (Products) MainActivity.prekes.get(i);
            TableRow tbrow = new TableRow(this);
            tbrow.setId(i);
            TextView t1v = new TextView(this);
            t1v.setText(test.getName());
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(""+test.getPrice());
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(""+test.getCal());
            tbrow.addView(t3v);
            EditText e4t = new EditText(this);
            e4t.setText("0");
            tbrow.addView(e4t);
            Switch s5 = new Switch(this);

            tbrow.addView(s5);
            stk.addView(tbrow);
        }

    }
}