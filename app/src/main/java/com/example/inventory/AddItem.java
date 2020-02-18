package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddItem extends AppCompatActivity {
    EditText edtAddItemDescription, edtAddItemLocation, edtAddItemQuantity, edtAddItemNotes;
    Button btnAddItem;

    private Button btnAddItemClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        edtAddItemDescription = findViewById(R.id.edtAddItemDescription);
        edtAddItemLocation = findViewById(R.id.edtAddItemLocation);
        edtAddItemQuantity = findViewById(R.id.edtAddItemQuantity);
        edtAddItemNotes = findViewById(R.id.edtAddItemNotes);
        btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItemClear = findViewById(R.id.btnAddItemClear);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseObject items = new ParseObject("Items");
                items.put("description", edtAddItemDescription.getText().toString());
                items.put("location", edtAddItemLocation.getText().toString());
                items.put("quantity", edtAddItemQuantity.getText().toString());
                items.put("notes", edtAddItemNotes.getText().toString());
                items.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                        Toast.makeText(AddItem.this, items.get("description") + " was added to inventory",Toast.LENGTH_SHORT).show();
                    }
                        else{
                            Toast.makeText(AddItem.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }});
            }
        });

        btnAddItemClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAddItemDescription.setText("");
                edtAddItemLocation.setText("");
                edtAddItemQuantity.setText("");
                edtAddItemNotes.setText("");

            }
        });

    }
}
