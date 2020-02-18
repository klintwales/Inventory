package com.example.inventory;

import
        androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity implements RecyclerAdapter.OnItemListener {

    private static final String TAG = "tag";
    private String allDescriptions;
    private String allLocations;
    private String allQuantities;


    private RecyclerView rvItems;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();

    private EditText edtItemLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        rvItems = findViewById(R.id.rvItems);



        // Queries db for all object descriptions, locations, and quantities then sets them in a recycler view

        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject items : objects){
                            allDescriptions = items.get("description") + "";
                            allLocations = items.get("location") + "";
                            allQuantities = items.get("quantity") + "";

                            descriptions.add(allDescriptions);
                            locations.add(allLocations);
                            quantity.add(allQuantities);

                        initRecyclerView();

                    }
                }
            }



        });




    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView started");

        RecyclerView recyclerView = findViewById(R.id.rvItems);
        RecyclerAdapter adapter = new RecyclerAdapter(descriptions, locations, quantity, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getApplicationContext(), "was clicked " + descriptions.get(position), Toast.LENGTH_SHORT).show();
    }
}
