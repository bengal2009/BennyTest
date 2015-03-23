package com.example.blin.bennytest.GPS;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.blin.benlib.BenGps;
import com.example.blin.bennytest.R;

public class GPSDEMO extends ActionBarActivity {
    String TAG = "GPSDEMO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsdemo);

    }

    public void GpsTest(View v)
    {
        String address = "";
        BenGps.GPSService mGPSService = new BenGps.GPSService(this);
        mGPSService.getLocation();
        if (mGPSService.isLocationAvailable == false) {

            // Here you can ask the user to try again, using return; for that
            Toast.makeText(this, "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
            return;

            // Or you can continue without getting the location, remove the return; above and uncomment the line given below
            // address = "Location not available";
        } else {

            // Getting location co-ordinates
            double latitude = mGPSService.getLatitude();
            double longitude = mGPSService.getLongitude();
            Toast.makeText(this, "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

            address = mGPSService.getLocationAddress();
            Log.i(TAG,"Latitude: " + latitude + " \nLongitude: " + longitude);
//            tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
//            tvAddress.setText("Address: " + address);
        }

        Toast.makeText(this, "Your address is: " + address, Toast.LENGTH_SHORT).show();

        // make sure you close the gps after using it. Save user's battery power
        mGPSService.closeGPS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpsdemo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
