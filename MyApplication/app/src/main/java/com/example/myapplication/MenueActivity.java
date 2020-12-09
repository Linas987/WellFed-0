package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import HelpeClasses.Products;
import HelpeClasses.User;

import static java.lang.Math.round;

public class MenueActivity extends AppCompatActivity {
    TableLayout stk;
    static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        final TableLayout TableL=(TableLayout)findViewById(R.id.TableL);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        System.out.println(" Welcome "+user.getUsername());
        System.out.println(" ID ----> "+user.get_id());
        nameText.setText(" Welcome "+user.getUsername());



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

                ArrayList<String> selectedproducts=new ArrayList();

                int CalSum=0;
                double PriceSum=0;
                for(int i =0,j = stk.getChildCount(); i< j ;i++)
                {
                    try {
                        //get Child views of the table with table.getChildAt(int index), this returns a view
                        //cast it to a TextView
                        View vi = stk.getChildAt(i);
                        TableRow r = (TableRow) vi;
                        //in this row (row i) of the table get the child element(column) where the first column would have a value of 0

                        TextView getQan = (TextView) r.getChildAt(3);
                        String Qan = getQan.getText().toString();

                        TextView getCalSum = (TextView) r.getChildAt(2);
                        String gCnum = getCalSum.getText().toString();

                        TextView getPriceSum = (TextView) r.getChildAt(1);
                        String gPsum = getPriceSum.getText().toString();

                        TextView getPname = (TextView) r.getChildAt(0);
                        String PNo = getPname.getText().toString();

                        if(Integer.parseInt(Qan) != 0)
                        {
                            //grab individual items and store them: name (PNo) and quantity (Qan)
                            selectedproducts.add(PNo+" x "+Qan);

                            CalSum=CalSum+Integer.parseInt(gCnum)*Integer.parseInt(Qan);
                            PriceSum=PriceSum+Double.parseDouble(gPsum)*Integer.parseInt(Qan);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                DecimalFormat df=new DecimalFormat("0.00");
                String numsum=("Price: "+df.format(PriceSum)+"-\n Calories: "+CalSum+"-\n Recommended calorie input per day: "+(((user.getHeight()*6.25)+(user.getWeight()*10))-(5*user.getAge())-80));
                System.out.println(user.getUsername()+" "+user.getHeight()+" "+user.getWeight()+" "+user.getAge());
                System.out.println(numsum);


                //for(int i =0,j = selectedproducts.size(); i< j ;i++)
                //{System.out.println(selectedproducts.get(i));}



                Intent startIntent =new Intent(getApplicationContext(), Cart.class);
                Cart.selectedproducts=selectedproducts;
                Cart.moreInfo=numsum;
                startActivity(startIntent);
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
        FloatingActionButton  ch= (FloatingActionButton) findViewById(R.id.changepar);
        ch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Log.d( "WellFed0","Succsess");
                Intent startIntent =new Intent(getApplicationContext(), Change.class);
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

        stk.addView(tbrow0);
        for (int i = 0; i<MainActivity.productDataBase.size(); i++) {
            Products test= (Products) MainActivity.productDataBase.get(i);
            TableRow tbrow = new TableRow(this);
            tbrow.setId(i);
            TextView t1v = new TextView(this);
            t1v.setText(test.getName());
            t1v.setWidth(500);
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

            stk.addView(tbrow);
        }

    }
}