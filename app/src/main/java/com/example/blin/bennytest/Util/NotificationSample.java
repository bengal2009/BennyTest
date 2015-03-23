package com.example.blin.bennytest.Util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.blin.bennytest.R;

public class NotificationSample extends ActionBarActivity {
    NotificationManager NM;
    EditText one,two,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sample);
        one = (EditText)findViewById(R.id.editText1);
        two = (EditText)findViewById(R.id.editText2);
        three = (EditText)findViewById(R.id.editText3);

    }
    public void notify(View vobj){
        String title = one.getText().toString();
        String subject = two.getText().toString();
        String body = three.getText().toString();
        NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.
                stat_notify_more,title,System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(
                getApplicationContext(),0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
        NM.notify(0, notify);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_sample, menu);
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
