package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Items extends AppCompatActivity {

    private String allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        allItems = "";

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
        });

    }
}
