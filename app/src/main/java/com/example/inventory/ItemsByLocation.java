package com.example.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ItemsByLocation extends AppCompatActivity implements RecyclerAdapter.OnItemListener {
    private static final String TAG = "tag";

    private String allObjectIds;
    private String allDescriptions;
    private String allLocations;
    private String allQuantities;
    private String allNotes;
    private String location;

    private ArrayList<String> objectIds = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_by_location);


    }

    @Override
    public void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        locations.clear();
        Intent intent = getIntent();
        location = intent.getStringExtra("location");
        query();
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView started");


        RecyclerView recyclerView = findViewById(R.id.rvItemsByLocation);
        RecyclerAdapter adapter3 = new RecyclerAdapter(descriptions, quantity, this, this);
        recyclerView.setAdapter(adapter3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void query() {
        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.whereEqualTo("location", location + "");
        List<ParseObject> objects = new ArrayList<ParseObject>();
        objectIds.clear();
        descriptions.clear();
        locations.clear();
        quantity.clear();
        notes.clear();

        try {
            List<ParseObject> results = queryAll.find();
            for (ParseObject result : results) {

                    allObjectIds = result.getObjectId();
                    allDescriptions = result.get("description") + "";
                    allLocations = result.get("location") + "";
                    allQuantities = result.get("quantity") + "";
                    allNotes = result.get("notes") + "";

                    objectIds.add(allObjectIds);
                    descriptions.add(allDescriptions);
                    locations.add(allLocations);
                    quantity.add(allQuantities);
                    notes.add(allNotes);

                    initRecyclerView();
                    //Toast.makeText(this,  "Object found " + result.getObjectId(), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ItemDetails.class);
        intent.putExtra("objectId", objectIds.get(position));
        intent.putExtra("description", descriptions.get(position));
        intent.putExtra("location", locations.get(position));
        intent.putExtra("quantity", quantity.get(position));
        intent.putExtra("notes", notes.get(position));
        intent.putExtra("Locations", locations);
        startActivity(intent);

    }
}
