package HelpeClasses;

import com.example.myapplication.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GETResponses {
    ArrayList updatedArr =new ArrayList();
    ArrayList updatedArr2 =new ArrayList();
    public void ResponceToArr()
    {
        /**
         * This part is responsible for building the request http for Products
         */
        OkHttpClient client = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url("http://172.105.246.14:3000/products")//http://172.105.246.14:3000/products  https://wellfed-27ec.restdb.io/rest/products
                //.addHeader("x-apikey", "c5c483eda1953687c8379598b40b2205ed77a")
                .build();
        //System.out.println("-------------------------------------");
        /**
         * This part is responsible for building the request http for Users
         */
        Request request2 = new Request.Builder()
                .url("http://172.105.246.14:3000/users")//https://wellfed-27ec.restdb.io/rest/my-user http://172.105.246.14:3000/users
                //.addHeader("x-apikey", "c5c483eda1953687c8379598b40b2205ed77a")
                .build();

        /**
         * This section is responsible for the request http assignment to an ArrayList (products)
         */
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String myresponce = response.body().string();
                    System.out.println(myresponce);
                    Gson gson= new Gson();
                    try {
                        JSONArray jsonArr = new JSONArray(myresponce);
                        for (int i = 0; i < jsonArr.length(); i++)
                        {
                            JSONObject jsonObj = jsonArr.getJSONObject(i);
                            //System.out.println(jsonObj);
                            Products prod=gson.fromJson(String.valueOf(jsonObj),Products.class);
                            //System.out.println(prod.getCal());
                            updatedArr.add(prod);
                        }
                        MainActivity.productDataBase = updatedArr;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        /**
         * This section is responsible for the request http assignment to an ArrayList (users)
         */
        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String myresponce = response.body().string();
                    System.out.println(myresponce);
                    Gson gson= new Gson();
                    try {
                        JSONArray jsonArr = new JSONArray(myresponce);
                        for (int i = 0; i < jsonArr.length(); i++)
                        {
                            JSONObject jsonObj = jsonArr.getJSONObject(i);
                            //System.out.println(jsonObj);
                            User usr=gson.fromJson(String.valueOf(jsonObj),User.class);
                            //System.out.println(prod.getCal());
                            updatedArr2.add(usr);
                        }
                        MainActivity.userDataBase = updatedArr2;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}
