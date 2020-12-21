package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import HelpeClasses.JavaMailAPI;
import HelpeClasses.User;

public class Cart extends AppCompatActivity {



    static ArrayList<String> selectedproducts;
    static String moreInfo;

    /**
     * Method that formats an array list to a string type with brake lines
     */

    private String ConfertArrayListToFormatedString (ArrayList<String> aString)
    {
        String sp = "";
        for(int i=0;i<aString.size();i++) {
            //System.out.println(sp+selectedproducts.get(i)+"\n");

            sp=(sp+aString.get(i)+"\n");
        }
        return sp;
    }

    /**
     * Method that sends a mail
     */

    private void sendMail(String message1, String subject1) {

        String mail = MenueActivity.user.getEmail();//
        String message = message1;
        String subject = subject1;

        String keeptrack="wellfed2020@gmail.com";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();

        JavaMailAPI javaMailAPI2 = new JavaMailAPI(this,keeptrack,subject,message);
        javaMailAPI2.execute();

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


                System.out.println(user.getEmail());

                String subject = location.getText().toString();
                int iend = moreInfo.indexOf("-");
                String subString="";
                if (iend != -1)
                {
                    subString= moreInfo.substring(0 , iend);
                }

                System.out.println(subString);
                String message = subString+" \n "+ConfertArrayListToFormatedString(selectedproducts)+"sent from : "+user.getEmail();

                sendMail(message,subject);

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