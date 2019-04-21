package com.fsemicolon.earthquake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //TODO: create a list_view.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView rootListView = findViewById(R.id.root_list_view);


        ArrayList<Earthquake> earthquakes =QueryUtils.extractEarthquakes();




        /*
        Creating an Earthquake Adapter to connect our ArrayLists and ListView
         */

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,earthquakes);


        rootListView.setAdapter(earthquakeAdapter);
    }
}
