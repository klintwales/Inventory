package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class AddItem extends AppCompatActivity {
    EditText edtAddItemDescription, edtAddItemLocation, edtAddItemQuantity, edtAddItemNotes;
    TextView tvAddItemLocation;
    Button btnAddItem;
    Spinner spnItemLocation;

    private static final String TAG = "tag";
    private String allObjectIds;
    private String allDescriptions;
    private String allLocations;
    private String allQuantities;
    private String allNotes;

    private ArrayList<String> objectIds = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private List<String> locations = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();

    private List<String> temp = new ArrayList<>();


    private Button btnAddItemClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        edtAddItemDescription = findViewById(R.id.edtAddItemDescription);

        edtAddItemQuantity = findViewById(R.id.edtAddItemQuantity);
        edtAddItemNotes = findViewById(R.id.edtAddItemNotes);
        tvAddItemLocation = findViewById(R.id.tvAddItemLocation);
        btnAddItem = findViewById(R.id.btnAddItem);
        spnItemLocation = findViewById(R.id.spnAddItemLocation);

        btnAddItemClear = findViewById(R.id.btnAddItemClear);

        temp.add("test item");

        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject items : objects){

                        allObjectIds = items.getObjectId();
                        allDescriptions = items.get("description") + "";
                        allLocations = items.get("location") + "";
                        allQuantities = items.get("quantity") + "";
                        allNotes = items.get("notes") + "";

                        objectIds.add(allObjectIds);
                        descriptions.add(allDescriptions);
                        locations.add(allLocations);
                        quantity.add(allQuantities);
                        notes.add(allNotes);

                        initSpinner();

                    }
                }
            }
        });



        Toast.makeText(AddItem.this, locations + "", Toast.LENGTH_LONG).show();
        //SPINNER



        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseObject items = new ParseObject("Items");
                items.put("description", edtAddItemDescription.getText().toString());
                items.put("location", spnItemLocation.getSelectedItem().toString());
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
                //spnItemLocation.setText("");
                edtAddItemQuantity.setText("");
                edtAddItemNotes.setText("");

            }
        });

        spnItemLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item = spnItemLocation.getSelectedItem().toString();
                //tvAddItemLocation.setText(locations.get(i));
                Toast.makeText(AddItem.this, "onItemSelected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddItem.this, "onNothing", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnItemLocation.setAdapter(adapter);
    }
}
