package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Locations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        getActionBar().setTitle("Locations");
    }
}
