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

        try {
            List<ParseObject> results = queryAll.find();
            for (ParseObject result : results) {
                    allObjectIds = result.getObjectId();
                    allDescriptions = result.get("description") + "";
                    allQuantities = result.get("quantity") + "";

                    objectIds.add(allObjectIds);
                    descriptions.add(allDescriptions);
                    quantity.add(allQuantities);

                    Toast.makeText(this,  "Object found " + descriptions, Toast.LENGTH_LONG).show();
                    initRecyclerView();
                    //Toast.makeText(this,  "Object found " + result.getObjectId(), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {


    }
}
