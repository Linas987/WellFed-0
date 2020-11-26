package HelpeClasses;


import android.os.Build;
import androidx.annotation.RequiresApi;


import com.example.myapplication.MainActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * This class is for the user registration, authentication and other actions
 */
    public class UActions {
        /**
         * Method that gets SHA-256 hash.
         */
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public byte[] getSHA(String input) throws NoSuchAlgorithmException
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+md.digest(input.getBytes(StandardCharsets.UTF_8))+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }

        /**
         * Method that  simulates hash
         */
        public String toHexString(byte[] hash)
        {
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32)
            {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        }

        /**
         * SignUp method for users to complete registration & write to database
         * @throws NoSuchAlgorithmException
         */
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public User signup(String Username, String Password,String Email, int Weigth, int Heigth, int Age) throws IOException, NoSuchAlgorithmException
        {
            for (int i = 0; i < MainActivity.userDataBase.size(); i++) {
                User test = (User) MainActivity.userDataBase.get(i);
                System.out.println(test.getUsername());
            }

            String hashedPassword = toHexString(getSHA(Password));
            System.out.println(hashedPassword);

            if (!checkRegisterCredentials(Username, hashedPassword))//check if a user already exists
            {
            RequestBody requestBody = new FormBody.Builder()
                        .add("username", Username)
                        .add("password", hashedPassword)
                        .add("email", Email)
                        .add("weight", String.valueOf(Weigth))
                        .add("height", String.valueOf(Heigth))
                        .add("age", String.valueOf(Age))
                        .build();

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("https://wellfed-27ec.restdb.io/rest/my-user")
                        .post(requestBody)
                        .addHeader("x-apikey", "c5c483eda1953687c8379598b40b2205ed77a")
                        .addHeader("Content-Type", "application/json")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponse = response.body().string();
                            System.out.println(myResponse);
                            Gson gson= new Gson();
                            try {
                                JSONObject jsonObj = new JSONObject(myResponse);

                                    System.out.println("-----------------------------"+jsonObj+"---------------------------------");
                                    User usr=gson.fromJson(String.valueOf(jsonObj),User.class);

                                MainActivity.userDataBase.add(usr);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                return new User(Username,hashedPassword,Email,Weigth,Heigth,Age);
            }
            else
            {
               System.out.println("Password or username already in use, please use a unique combination of username and password");
               return null;
            }

        }
        /**
         * method for users to log in
         * @throws NoSuchAlgorithmException
         */
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public User login(String Username, String Password) throws IOException, NoSuchAlgorithmException
        {
            String hashedPassword = toHexString(getSHA(Password));
            User usr=new User(" ", " "," ", 0, 0, 0);//temporary user that will be filled in
            if (checkLoginCredentials(Username, hashedPassword,usr))
            {
                System.out.println("entered");
                return usr;
            }
            return null;

        }


        /**
         * Method check user information for registration from database and textfields
         */
        public boolean checkRegisterCredentials(String Username, String hashedPassword) throws IOException {

            System.out.println("comparing with :" + Username+" "+hashedPassword);
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < MainActivity.userDataBase.size(); i++) {
                User test = (User) MainActivity.userDataBase.get(i);
                System.out.println(i+" passing test ->" + test.getUsername()+" "+test.getHashedPassword());


                if (test.getUsername().equals(Username) || test.getHashedPassword().equals(hashedPassword)) {
                    System.out.println("found matching elements");
                    return true;
                }

            }
            return false;
        }

        /**
        * Method check user information for login from database and textfields
        */
        public boolean checkLoginCredentials(String Username, String hashedPassword, User usr) throws IOException {

        System.out.println("comparing with :" + Username+" "+hashedPassword);
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < MainActivity.userDataBase.size(); i++) {
            User test = (User) MainActivity.userDataBase.get(i);
            System.out.println(i+" passing test ->" + test.getUsername()+" "+test.getHashedPassword());


            if((test.getUsername().equals(Username) && test.getHashedPassword().equals(hashedPassword))) {
                System.out.println("found matching elements");
                usr.setUsername(test.getUsername());
                usr.setHashedPassword(test.getHashedPassword());
                usr.setEmail(test.getEmail());
                usr.setWeight(test.getWeight());
                usr.setHeight(test.getHeight());
                usr.setAge(test.getAge());
                return true;
            }

        }
        return false;
        }

}

