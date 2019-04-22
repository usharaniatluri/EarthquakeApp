package com.fsemicolon.earthquake;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
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



        TextView dateTextView = listItemView.findViewById(R.id.date_text_view);

        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);

        //Now we are going to set the text from the current objects
        //Sometimes the magnitudes are in the range of 7.298 but we only need 7.2
        //So we are going to use decimal formatter

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        String magnitudeString = decimalFormat.format(currentEarthquake.getMagnitude());


        magnitude.setText(magnitudeString);



        /**
         * We have to convert UNIX Time to Regular Date
         */

        // Create a new Date object from the time in milliseconds of the earthquake

        Date dateObject = new Date(currentEarthquake.getTime());

        // Format the date string (i.e. "Mar 3, 1984")

        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView

        dateTextView.setText(formattedDate);

        // Format the time string (i.e. "4:30PM")

        String formattedTime = formatTime(dateObject);

        // Display the time of the current earthquake in that TextView

        timeTextView.setText(formattedTime);


        /**
         * Let's split the locations based on location offset and primary location
         */

        String originalLocation = currentEarthquake.getCity();

        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.

        String primaryLocation;

        String locationOffset;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {

            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt".

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;

            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        }
        else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".

            locationOffset = getContext().getString(R.string.near_the);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".

            primaryLocation = originalLocation;
        }

        //TextView for Offset Location

        TextView offsetLocationTextView = listItemView.findViewById(R.id.offset_location_text_view);

        offsetLocationTextView.setText(locationOffset);

        //TextView for Primary Location

        TextView primaryLocationTextView = listItemView.findViewById(R.id.primary_city_location);

        primaryLocationTextView.setText(primaryLocation);




        return listItemView;
    }
}
