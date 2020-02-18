package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import static com.parse.Parse.getApplicationContext;

public class ItemDetails extends AppCompatActivity{

    private EditText edtItemDetailsDescription, edtItemDetailsLocation, edtItemDetailsQuantity, edtItemDetailsNotes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        edtItemDetailsDescription = findViewById(R.id.edtItemDetailsDescription);
        edtItemDetailsLocation = findViewById(R.id.edtItemDetailsLocation);
        edtItemDetailsQuantity = findViewById(R.id.edtItemDetailsQuantity);
        edtItemDetailsNotes = findViewById(R.id.edtItemDetailsNotes);

        //get items from intent
        Intent intent = getIntent();
        String descriptions = intent.getStringExtra(Items.DESCRIPTION);
        String location = intent.getStringExtra(Items.LOCATION);
        String quantity = intent.getStringExtra(Items.QUANTITY);

        //set ui components to what item was clicked
        Toast.makeText(this, quantity, Toast.LENGTH_SHORT).show();
        edtItemDetailsDescription.setText(descriptions);
        edtItemDetailsLocation.setText(location);
        edtItemDetailsQuantity.setText(quantity);


    }
}
