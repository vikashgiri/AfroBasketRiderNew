package com.afrobaskets.App.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afrobaskets.App.interfaces.SavePref;
import com.webistrasoft.org.ecommerce.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    if(SavePref.getPref(SplashScreen.this,SavePref.is_loogedin).equalsIgnoreCase("true"))
                    {
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));

                    }
                    else {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    }
finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timerThread.start();
    }
}

