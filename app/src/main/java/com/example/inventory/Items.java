package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity {

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

        edtItemLocation = findViewById(R.id.edtItemLocation);



        /*allItems = "";

        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){

                    if(objects.size() > 0){

                        for (ParseObject items : objects){
                            allItems = allItems + items.get("description") + "\n";
                        }

                        Toast.makeText(Items.this, allItems, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(Items.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Items.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }


        });*/



        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Items");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject items : objects){
                            allDescriptions = items.get("description") + "";
                            allLocations = items.get("location") + "";
                            allQuantities = items.get("quantity") + "";

                            for (int i = 0; i < objects.size(); i++){
                            descriptions.add(allDescriptions);
                            locations.add(allLocations);
                            quantity.add(allQuantities);
                        }
                    }
                }
            }



        });

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        RecyclerAdapter adapter = new RecyclerAdapter(descriptions, locations, quantity, this  );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
