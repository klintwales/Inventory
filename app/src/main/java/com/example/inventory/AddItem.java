package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
    EditText edtAddItemDescription, edtAddItemLocation, edtAddItemQuantity;
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        edtAddItemDescription = findViewById(R.id.edtAddItemDesciption);
        edtAddItemLocation = findViewById(R.id.edtAddItemLocation);
        edtAddItemQuantity = findViewById(R.id.edtAddItemQuantity);
        btnAddItem = findViewById(R.id.btnAddItem);


    }
}
