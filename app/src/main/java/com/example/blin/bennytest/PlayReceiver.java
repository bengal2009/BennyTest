package com.example.blin.bennytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by blin on 2015/3/12.
 */
public class PlayReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bData = intent.getExtras();
        if(bData.get("msg").equals("play_hskay"))
        {
            Toast.makeText(context, "Click! ",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
