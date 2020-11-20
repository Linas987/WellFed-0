package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import HelpeClasses.UActions;
import HelpeClasses.User;

public class RegistracionController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracion_controller);

        Button Back=(Button) findViewById(R.id.Back);
        Button Confirm=(Button) findViewById(R.id.Confirm);
        final EditText Username=(EditText) findViewById(R.id.Username);
        final EditText Password=(EditText) findViewById(R.id.Password);
        final EditText Email=(EditText) findViewById(R.id.Email);
        final EditText Heigth=(EditText) findViewById(R.id.Heigth);
        final EditText Weigth=(EditText) findViewById(R.id.Weigth);
        final EditText Age=(EditText) findViewById(R.id.Age);

        for(int i=0;i<MainActivity.naudotojai.size();i++) {
            User test = (User) MainActivity.naudotojai.get(i);
            System.out.println(test.getUsername());
        }

        Confirm.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick (View v)
            {
                Log.d( "WellFed0","Succsess");
                UActions usr= new UActions();
                try {
                    User user= usr.signup(String.valueOf(Username.getText()), String.valueOf(Password.getText()), String.valueOf(Email.getText()), Integer.parseInt(String.valueOf(Heigth.getText())), Integer.parseInt(String.valueOf(Weigth.getText())), Integer.parseInt(String.valueOf(Age.getText())));
                    if (user == null)
                        Snackbar.make(v, "Password or username already in use, please use a unique combination of username and password", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }


            }
        });


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