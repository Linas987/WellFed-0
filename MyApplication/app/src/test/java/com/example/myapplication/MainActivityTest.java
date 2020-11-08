/*package com.example.myapplication;

import android.view.View;
import android.widget.Button;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void onCreate() throws Exception {
        View user = mActivity.findViewById(R.id.Username);
        View pass = mActivity.findViewById(R.id.Password);
        Button log =(Button) mActivity.findViewById(R.id.Login);
        Button reg =(Button) mActivity.findViewById(R.id.Registration);
        assertNotNull(user); assertNotNull(pass); assertNotNull(log); assertNotNull(reg);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}*/