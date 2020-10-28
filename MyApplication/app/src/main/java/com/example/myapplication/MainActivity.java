package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import HelpeClasses.GETResponses;
import HelpeClasses.Products;
import HelpeClasses.UActions;
import HelpeClasses.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static ArrayList prekes;

    public static ArrayList naudotojai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GETResponses grs= new GETResponses();
        grs.ResponceToArr();

        final TextView username =(TextView) findViewById(R.id.Username);
        final TextView password =(TextView) findViewById(R.id.Password);

        /*for(int i=0;i<pgr.size();i++)
        {
            Products test= (Products) pgr.get(i);
            System.out.println(" Name "+test.getName());
            //pnamelist.add(test.getName());
        }*/

        Button Registration=(Button) findViewById(R.id.Registration);

        Registration.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Log.d( "Register","Succsess R");
                Intent startIntent =new Intent(getApplicationContext(), RegistracionController.class);
                startActivity(startIntent);
            }
        });
        Button Login=(Button) findViewById(R.id.Login);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick (View v)
            {
                UActions uact = new UActions();
                try {
                    if(uact.login(String.valueOf(username.getText()),String.valueOf(password.getText()))!=null)
                    {
                        Log.d("Login", "Succsess L");
                        User usr=uact.login(String.valueOf(username.getText()),String.valueOf(password.getText()));
                        Intent startIntent = new Intent(getApplicationContext(), MenueActivity.class);
                        startActivity(startIntent);
                    }
                    else
                    {
                        System.out.println("password or username incorrect!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}