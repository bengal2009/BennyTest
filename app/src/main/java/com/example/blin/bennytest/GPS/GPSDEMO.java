package com.example.blin.bennytest.GPS;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.blin.benlib.BenGps;
import com.example.blin.benlib.BenUtil;
import com.example.blin.bennytest.R;

public class GPSDEMO extends ActionBarActivity {
    String TAG = "GPSDEMO";
    private static final String[] TEXTS = { "Image #1", "Image #2", "Image #3" };
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private TextSwitcher mTextSwitcher2;
    BenUtil A1=new BenUtil();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsdemo);
        TextView t2=(TextView) findViewById(R.id.textView3);
        t2.setText("System ID:"+A1.SystetemID(this));
        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mTextSwitcher2 = (TextSwitcher) findViewById(R.id.textSwitcher2);
        A1.BenAnimateText(this,mTextSwitcher2);
        mTextSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplication());
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);


    }
    public void NoClick(View view) {
        A1.ShareIntent(this);
    }
    public void onSwitch(View view) {
        mTextSwitcher.setText(TEXTS[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
    }
    public void onSwitch2(View view) {
        mTextSwitcher2.setText(TEXTS[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
    }


    public void TestGPS(View v)
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
