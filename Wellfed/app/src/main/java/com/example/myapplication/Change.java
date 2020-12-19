package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import HelpeClasses.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Change extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        final EditText Heigth=(EditText) findViewById(R.id.Heigth2);
        final EditText Weigth=(EditText) findViewById(R.id.Weigth2);
        final EditText Age=(EditText) findViewById(R.id.Age2);
        Button submit=(Button) findViewById(R.id.changer);
        Button Back=(Button) findViewById(R.id.back);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+MenueActivity.user.getUsername());

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (Heigth.getText().toString().matches("")||Weigth.getText().toString().matches("")||Age.getText().toString().matches(""))
                {
                    Snackbar.make(v, "not all inputs are filled! Please fill them all", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                else
                {
                    String He = String.valueOf(Heigth.getText());
                    String We = String.valueOf(Weigth.getText());
                    String A = String.valueOf(Age.getText());


                    RequestBody requestBody = new FormBody.Builder()
                            .add("weight", We)
                            .add("height", He)
                            .add("age", A)
                            .build();

                    //System.out.println(We);
                    //System.out.println(He);
                    //System.out.println(A);

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://193.219.91.103:4774/users?username=eq."+MenueActivity.user.getUsername())//"https://wellfed-27ec.restdb.io/rest/my-user" http://172.105.246.14:3000/users + MenueActivity.user.get_id()  http://193.219.91.103:4774/
                            .patch(requestBody)
                            //.put(requestBody)
                            //.addHeader("x-apikey", "c5c483eda1953687c8379598b40b2205ed77a")
                            .addHeader("Content-Type", "application/json")
                            .build();

                    //System.out.println(request);

                    System.out.println("???????????????????????????????");
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String myResponse = response.body().string();
                                System.out.println(myResponse);

                                //Gson gson= new Gson();
                                try {

                                    JSONObject jsonObj = new JSONObject(myResponse);

                                    System.out.println("-----------------------------" + jsonObj + "---------------------------------");
                                    //User usr=gson.fromJson(String.valueOf(jsonObj),User.class);
                                    //MainActivity.userDataBase.add(usr);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(startIntent);
                }

            }
        });
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