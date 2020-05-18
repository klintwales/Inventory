package com.example.inventory;

import
        androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity implements RecyclerAdapter.OnItemListener {

    private static final String TAG = "tag";
    private String allObjectIds;
    private String allDescriptions;
    private String allLocations;
    private String allQuantities;
    private String allNotes;


    private RecyclerView rvItems;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private ArrayList<String> objectIds = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();

    private EditText edtItemLocation;


    public static final String OBJECTIDS = "com.example.inventory.OBJECTIDS";
    public static final String DESCRIPTION = "com.example.inventory.DESCRIPTION";
    public static final String LOCATION = "com.example.inventory.LOCATION";
    public static final String QUANTITY = "com.example.inventory.QUANTITY";
    public static final String NOTES = "com.example.inventory.NOTES";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvItems = findViewById(R.id.rvItems);
        onStart();


    }

    @Override
    public void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    public void onResume(){
        super.onResume();
        //Toast.makeText(this, "onResume ", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_items);
        objectIds.clear();
        descriptions.clear();
        locations.clear();
        quantity.clear();
        notes.clear();



        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.whereNotEqualTo("description", "");
        List<ParseObject> objects = new ArrayList<ParseObject>();

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


/*
        // Queries db for all object descriptions, locations, and quantities then sets them in a recycler view
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

                        if() {
                            initRecyclerView();
                        }
                    }
                }
            }
        });*/

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView started");
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        RecyclerAdapter adapter = new RecyclerAdapter(descriptions, locations, quantity, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ItemDetails.class);
        /*objectIds.get(position);
        descriptions.get(position);
        locations.get(position);
        quantity.get(position);
        notes.get(position);*/
        //test toast to display ObjectIds
        //Toast.makeText(this, " " + notes, Toast.LENGTH_LONG).show();

        intent.putExtra("objectId", objectIds.get(position));
        intent.putExtra("description", descriptions.get(position));
        intent.putExtra("location", locations.get(position));
        intent.putExtra("quantity", quantity.get(position));
        intent.putExtra("notes", notes.get(position));
        intent.putExtra("Locations", locations);
        startActivity(intent);
    }

}


