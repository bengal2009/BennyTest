package com.example.blin.bennytest.GPS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.blin.bennytest.R;
//http://blog.csdn.net/android_ls/article/details/8583656
public class Baidumap extends ActionBarActivity implements
        OnGetGeoCoderResultListener {
    String TAG = "LocationDemo";
    private static final String LTAG = Baidumap.class.getSimpleName();
    // 定位相?
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    private Context mcontext;
    BitmapDescriptor mCurrentMarker;


    GeoCoder mSearch = null; // 搜索模?，也可去掉地?模??立使用
    BaiduMap mBaiduMap = null;
    MapView mMapView = null;
    // UI相?
    RadioGroup.OnCheckedChangeListener radioButtonListener;
    Button requestLocButton;
    boolean isFirstLoc = true;// 是否首次定位

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        mcontext=getApplicationContext();

        mCurrentMode = LocationMode.NORMAL;

        InitListener();

        // 地?初始化
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        // ??定位??
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打?gps
        option.setCoorType("bd09ll"); // ?置坐??型
        option.setScanSpan(1000);
        option.setProdName("Benny");
        mLocClient.setLocOption(option);
        mLocClient.start();
        // 初始化搜索模?，注?事件?听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
//        SWitch MapMode
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        mBaiduMap.setTrafficEnabled(((CheckBox) view).isChecked());
    }

    /*
     * onCreateOptionsMenu
     * #onCreateOptionsMenu(Menu menu)
     * @return int
       */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_baidumap, menu);
        return true;
    }
    /*
     * onOptionsItemSelected(MenuItem item)
     * #onOptionsItemSelected(MenuItem item)
     * @return int
       */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.offline) {
            Intent intent=new Intent();
//            intent.putExtra("x", e.geoPt.longitude);
//            intent.putExtra("y", e.geoPt.latitude);
            intent.setClass(this, OfflineDemo.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void InitListener()
    {
        requestLocButton = (Button) findViewById(R.id.button1);
        requestLocButton.setText("普通");
        View.OnClickListener btnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (mCurrentMode) {
                    case NORMAL:
                        requestLocButton.setText("跟?");
                        mCurrentMode = LocationMode.FOLLOWING;
                        mBaiduMap
                                .setMyLocationConfigeration(new MyLocationConfiguration(
                                        mCurrentMode, true, mCurrentMarker));
                        break;
                    case COMPASS:
                        requestLocButton.setText("普通");
                        mCurrentMode = LocationMode.NORMAL;
                        mBaiduMap
                                .setMyLocationConfigeration(new MyLocationConfiguration(
                                        mCurrentMode, true, mCurrentMarker));
                        break;
                    case FOLLOWING:
                        requestLocButton.setText("??");
                        mCurrentMode = LocationMode.COMPASS;
                        mBaiduMap
                                .setMyLocationConfigeration(new MyLocationConfiguration(
                                        mCurrentMode, true, mCurrentMarker));
                        break;
                }
            }
        };
        requestLocButton.setOnClickListener(btnClickListener);


    }
    public void TEST(View V)
    {
        BDLocation location;
        mSearch.geocode(new GeoCodeOption().city(
                "上海").address(
                "中山北路二段"));
//        Toast.makeText(getApplicationContext(), VersionInfo.getApiVersion(),
//                Toast.LENGTH_SHORT).show();
    }
    public void Repos(View V)
    {
        if (mLocClient != null && mLocClient.isStarted()){
            mLocClient.requestLocation();
            Log.i(TAG,"Request Location");
        }
    }
    public void GerLookup(View V)
    {
        BDLocation location;
        LatLng ptCenter = new LatLng(31.260164, 121.442622);
        // 反Geo搜索
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(ptCenter));

    }
    /**
     * 定位SDK?听函?
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view ??后不在?理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此??置??者?取到的方向信息，???0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
          /*  Log.i(TAG,"Latitude:"+Double.toString(location.getLatitude()));
            Log.i(TAG,"Longitude:"+Double.toString(location.getLongitude()));*/
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                /*Log.i(TAG,"Latitude:"+Double.toString(location.getLatitude()));
                Log.i(TAG,"Longitude:"+Double.toString(location.getLongitude()));*/


                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出???定位
        mLocClient.stop();
        // ??定位??
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "onGetGeoCodeResult抱歉", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        String strInfo = String.format("?度：%f ?度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(this, strInfo, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "Sorry! Not found!", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        Toast.makeText(this, result.getAddress(),
                Toast.LENGTH_LONG).show();

    }
}
