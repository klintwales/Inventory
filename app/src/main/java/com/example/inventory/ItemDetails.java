package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemDetails extends AppCompatActivity{

    private EditText edtItemDetailsDescription, edtItemDetailsQuantity, edtItemDetailsNotes;
    private Button btnItemDetailsSave;
    private Spinner spnItemDetailLocation;

    private static final String TAG = "tag";
    private String allObjectIds;
    private String allDescriptions;
    private String allLocations;
    private String allQuantities;
    private String allNotes;
    private String location;

    private ArrayList<String> objectIds = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private List<String> locations = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();

    private List<String> temp = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
    }
    @Override
    public void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    public void onResume(){
        super.onResume();

        Toast.makeText(ItemDetails.this, "onResume", Toast.LENGTH_SHORT).show();

            edtItemDetailsDescription = findViewById(R.id.edtItemDetailsDescription);
            spnItemDetailLocation = findViewById(R.id.edtItemDetailsLocation);
            edtItemDetailsQuantity = findViewById(R.id.edtItemDetailsQuantity);
            edtItemDetailsNotes = findViewById(R.id.edtItemDetailsNotes);
            btnItemDetailsSave = findViewById(R.id.btnItemDetailsSave);

            //get items from intent
            Intent intent = getIntent();
            String objectIds = intent.getStringExtra("objectId");
            String descriptions = intent.getStringExtra("description");
            location = intent.getStringExtra("location");
            String quantity = intent.getStringExtra("quantity");
                if(!intent.getStringExtra("notes").equals("null")) {
                 String notes = intent.getStringExtra("notes");
                 edtItemDetailsNotes.setText(notes);
                 }

            //set ui components to what item was clicked
        edtItemDetailsDescription.setText(descriptions);
        edtItemDetailsQuantity.setText(quantity);

        query();
        Collections.sort(locations, String.CASE_INSENSITIVE_ORDER);
        initSpinner();

            btnItemDetailsSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Items");
                    //Toast.makeText(ItemDetails.this, " start of btn save", Toast.LENGTH_SHORT).show();
                    // Retrieve the object by id
                    query.getInBackground(objectIds, new GetCallback<ParseObject>() {
                        public void done(ParseObject items, ParseException e) {
                            if (e == null) {
                                items.put("description", edtItemDetailsDescription.getText().toString());
                                items.put("location", spnItemDetailLocation.getSelectedItem().toString());
                                items.put("quantity", edtItemDetailsQuantity.getText().toString());
                                items.put("notes", edtItemDetailsNotes.getText().toString());

                                //Toast.makeText(ItemDetails.this, "update completed " + descriptions + e, Toast.LENGTH_SHORT).show();


                                items.saveInBackground();
                            } else {
                                Toast.makeText(ItemDetails.this, "update failed " + descriptions + e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    //Intent intent = new Intent(ItemDetails.this, Items.class);

                    //startActivity(intent);

                    //onBackPressed();
                }
            });
    }

    private void query() {
        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.whereNotEqualTo("location", "");
        List<ParseObject> objects = new ArrayList<ParseObject>();

        try {
            List<ParseObject> results = queryAll.find();
            for (ParseObject result : results) {
                if (!locations.contains(result.get("location") + "")) {
                    allLocations = result.get("location") + "";
                    locations.add(allLocations);



                }
                //Toast.makeText(this,  "Object found " + result.getObjectId(), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this,  "query failed", Toast.LENGTH_LONG).show();
        }
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnItemDetailLocation.setAdapter(adapter);
        spnItemDetailLocation.setSelection(locations.indexOf(location));

    }


}
