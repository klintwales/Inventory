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

public class AddLocation extends AppCompatActivity {

    EditText edtAddLocation;
    Button btnAddLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        edtAddLocation = findViewById(R.id.edtAddLocation);
        btnAddLocation = findViewById(R.id.btnAddLocation);

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseObject items = new ParseObject("Items");
                items.put("description", "");
                items.put("location", edtAddLocation.getText() + "");
                items.put("quantity", "");
                items.put("notes", "");
                items.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(AddLocation.this, items.get("description") + " was added to inventory",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddLocation.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }});
            }
        });
    }
}
