package com.example.inventory;

import android.app.Application;

import com.parse.Parse;
import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gUyrtAfVDpZyp3EmwIztWJtwZbriQ4VUhUO007DB")
                .clientKey("nnL1w2sI5gYwEI2uYP5ri6HtIT7vqEk1c7qoDMow")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
