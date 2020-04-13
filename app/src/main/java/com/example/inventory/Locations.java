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

        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject items : objects){

                        allLocations = items.get("location") + "";

                        locations.add(allLocations);




                        initRecyclerView();


                    }
                }
            }





        });


    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView started");


        RecyclerView recyclerView = findViewById(R.id.rvLocations);
        RecyclerAdapter adapter2 = new RecyclerAdapter(locations, this,this);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {

    }
}
