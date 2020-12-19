package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import HelpeClasses.User;

public class Cart extends AppCompatActivity {



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

        final User user=MenueActivity.user;

        FloatingActionButton Buy = (FloatingActionButton) findViewById(R.id.Buy);
        Buy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {

                if(location.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Please input the delivery location", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                String recipientList = (user.getEmail()+",wellfed2020@gmail.com");
                System.out.println(user.getEmail());
                String[] recipients = recipientList.split(",");
                String subject = location.getText().toString();
                int iend = moreInfo.indexOf("-");
                String subString="";
                if (iend != -1)
                {
                    subString= moreInfo.substring(0 , iend);
                }
                System.out.println(subString);
                String message = subString+" \n "+ConfertArrayListToFormatedString(selectedproducts)+"sent from : "+user.getEmail();

                Intent startIntent =new Intent(getApplicationContext(), Empty.class);
                startActivity(startIntent);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
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