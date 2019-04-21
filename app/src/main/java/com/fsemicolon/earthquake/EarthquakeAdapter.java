package com.fsemicolon.earthquake;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        //If the list View is null then we populate it with our List View

        if (listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);

        }

        /**
         * Now we are going to create an object for our Earthquake
         */

        Earthquake currentEarthquake = getItem(position);

        /**
         * Create TextViews for
         * 1. Magnitude
         * 2. City Name
         * 3. Time
         */

        TextView magnitude = listItemView.findViewById(R.id.magintude_text_view);

        TextView cityName = listItemView.findViewById(R.id.city_text_view);

        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);


        // set the text from the current objects

        magnitude.setText(""+currentEarthquake.getMagnitude());

        cityName.setText(currentEarthquake.getCity());

        /**
         * convert UNIX Time to Regular Date
         */

        long time = currentEarthquake.getTime();

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(time);

        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();

        /**
         *  set the formatted date to Time TextView
         */

        timeTextView.setText(date);

        String url = currentEarthquake.getUrl();

        /**
         * Opening Web Activity when the List Item View is clicked
         */





        return listItemView;
    }
}
