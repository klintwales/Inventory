package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnItems, btnLocations, btnAddItem, btnAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnItems = findViewById(R.id.btnItems);
        btnLocations = findViewById(R.id.btnLocations);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddLocation = findViewById(R.id.btnAddLocation);

        btnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItems();
            }
        });

    }

    public void openItems(){
        Intent intent = new Intent(this, Items.class);
        startActivity(intent);
    }

}
