package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.powermock.api.easymock.annotation.Mock;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.mockito.Mock;
//import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import android.util.Base64;

import HelpeClasses.GETResponses;
import HelpeClasses.Products;
import HelpeClasses.UActions;
import HelpeClasses.User;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void getSHA_and_toHexString_test() throws NoSuchAlgorithmException
    {
        UActions uactions =new UActions();//Declaring classes which we will need

        byte [] bit =uactions.getSHA("test123");//running methods
        String hex = uactions.toHexString(bit);//running methods

        Assert.assertEquals("ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae",hex);//testing outcome
    }

    @Test
    public void testlogin() throws IOException, NoSuchAlgorithmException {
        //MainActivity manact=new MainActivity();//Declaring classes which we will need
        UActions uactions =new UActions();
        User usr=new User("a","ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae","a",2,2,2);
        ArrayList<User> updatedArr2 =new ArrayList();
        updatedArr2.add(usr);
        MainActivity.userDataBase=updatedArr2;
        //Assert.assertEquals(1,MainActivity.userDataBase.size());
        //Assert.assertEquals("a",usr.getUsername());
        User usr2=uactions.login("a","test123");
        Assert.assertEquals("a",usr2.getUsername());
        Assert.assertEquals("ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae",usr2.getHashedPassword());
        Assert.assertEquals("a",usr2.getEmail());
        Assert.assertEquals(2,usr2.getWeight());
        Assert.assertEquals(2,usr2.getHeight());
        Assert.assertEquals(2,usr2.getAge());
    }

}
