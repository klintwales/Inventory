package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.*;
import static com.parse.Parse.getApplicationContext;

public class ItemDetails extends AppCompatActivity{

    private EditText edtItemDetailsDescription, edtItemDetailsLocation, edtItemDetailsQuantity, edtItemDetailsNotes;
    private Button btnItemDetailsSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
            edtItemDetailsDescription = findViewById(R.id.edtItemDetailsDescription);
            edtItemDetailsLocation = findViewById(R.id.edtItemDetailsLocation);
            edtItemDetailsQuantity = findViewById(R.id.edtItemDetailsQuantity);
            edtItemDetailsNotes = findViewById(R.id.edtItemDetailsNotes);
            btnItemDetailsSave = findViewById(R.id.btnItemDetailsSave);

            //get items from intent
            Intent intent = getIntent();
            String objectIds = intent.getStringExtra("objectId");
            String descriptions = intent.getStringExtra("description");
            String location = intent.getStringExtra("location");
            String quantity = intent.getStringExtra("quantity");
        if(!intent.getStringExtra("notes").equals("null")) {
            String notes = intent.getStringExtra("notes");
            edtItemDetailsNotes.setText(notes);
        }

            //set ui components to what item was clicked
                edtItemDetailsDescription.setText(descriptions);
                edtItemDetailsLocation.setText(location);
                edtItemDetailsQuantity.setText(quantity);

            btnItemDetailsSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Items");
                    Toast.makeText(ItemDetails.this, " start of btn save", Toast.LENGTH_SHORT).show();
                    // Retrieve the object by id
                    query.getInBackground(objectIds, new GetCallback<ParseObject>() {
                        public void done(ParseObject items, ParseException e) {
                            if (e == null) {
                                items.put("description", edtItemDetailsDescription.getText().toString());
                                items.put("location", edtItemDetailsLocation.getText().toString());
                                items.put("quantity", edtItemDetailsQuantity.getText().toString());
                                items.put("notes", edtItemDetailsNotes.getText().toString());

                                //Toast.makeText(ItemDetails.this, "update completed " + descriptions + e, Toast.LENGTH_SHORT).show();


                                items.saveInBackground();
                            } else {
                                Toast.makeText(ItemDetails.this, "update failed " + descriptions + e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(ItemDetails.this, "intent attempt ", Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(ItemDetails.this, Items.class);

                    //startActivity(intent);

                    //onBackPressed();
                }
            });
    }
}
