package com.example.blin.bennytest.GPS;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.blin.bennytest.R;

public class GPSSample extends ActionBarActivity implements LocationListener {
    private static final String TAG = "LocationActivity";
    private LocationManager locationMgr;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpssample);
        this.insert2tv("onCreate...");
        // 主角
        this.locationMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        // 取得位置提供者，不下條件，讓系統決定最適用者，true 表示生效的 provider
        String provider = this.locationMgr.getBestProvider(new Criteria(), true);
        if (provider == null) {
            this.insert2tv("沒有 location provider 可以使用");
            return;
        }
        this.insert2tv("取得 provider - " + provider);

        this.insert2tv("requestLocationUpdates...");        // 註冊 listener，兩個 0 不適合在實際環境使用，太耗電
        this.locationMgr.requestLocationUpdates(provider, 0, 0, this);

        this.insert2tv("getLastKnownLocation...");
        Location location = this.locationMgr.getLastKnownLocation(provider);
        if (location == null) {
            this.insert2tv("未取過 location");
            return;
        }
        this.insert2tv("取得上次的 location");
        this.onLocationChanged(location);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.insert2tv("removeUpdates...");
        this.locationMgr.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.insert2tv("onLocationChanged...");
        String msg = "經度: " + location.getLongitude() + ", 緯度: "
                + location.getLatitude();
        this.insert2tv(msg);
    }

    private void insert2tv(String msg) {
        if (this.tv == null) {
            this.tv = (TextView) this.findViewById(R.id.tv);
        }
        this.tv.setText(this.tv.getText().toString() + "\n" + msg);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        this.insert2tv("onStatusChanged...");
        // 當 location provider 改變時
    }

    @Override
    public void onProviderEnabled(String provider) {
        this.insert2tv("onProviderEnabled...");
        // 當 location provider 有效時
    }

    @Override
    public void onProviderDisabled(String provider) {
        this.insert2tv("onProviderDisabled...");
        // 當 location provider 無效時
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpssample, menu);
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
