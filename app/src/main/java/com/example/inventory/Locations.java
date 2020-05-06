package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Locations extends AppCompatActivity implements RecyclerAdapter.OnItemListener {

    private static final String TAG = "tag";

    private String allLocations;

    private ArrayList<String> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        onStart();
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
        query();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView started");


        RecyclerView recyclerView = findViewById(R.id.rvLocations);
        RecyclerAdapter adapter2 = new RecyclerAdapter(locations, this, this);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {

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


                    initRecyclerView();
                }
                //Toast.makeText(this,  "Object found " + result.getObjectId(), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
