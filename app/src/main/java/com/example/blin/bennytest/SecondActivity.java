package com.example.blin.bennytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.blin.benlib.BenNetwork;
import com.example.blin.benlib.BenUtil;


public class SecondActivity extends ActionBarActivity {
    String TAG="Second Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView A3 = (TextView) findViewById(R.id.textView2);
        A3.setText("Hello!");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }
public void ShowDia(View v)
{
    BenUtil A1=new BenUtil();
    A1.Bendialog1(this,"Do you want quit?");
    Log.i(TAG,"ShowDia");
}
    public void ShowDia1(View v)
    {
        String S1;
        BenUtil  A1=new BenUtil();
        A1.Dialog_Input(this);

//        Log.i(TAG,S1);
    }
//  Service Test
public void StService(View v)
{
    Intent intent = new Intent(this, HelloService.class);
    startService(intent);

}
    public void StopSvc(View v)
    {
        Intent intent = new Intent(this, HelloService.class);
        stopService(intent);

    }
    public void RDINT(View v)
    {
        final String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";
        TextView A3 = (TextView) findViewById(R.id.textView2);

        Log.i(TAG,"Start");
        BenNetwork.AsyncHttpTask A2=new BenNetwork.AsyncHttpTask(this,A3);
        A2.execute(url);

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
