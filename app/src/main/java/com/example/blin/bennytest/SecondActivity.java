package com.example.blin.bennytest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blin.benlib.BenFileIO;
import com.example.blin.benlib.BenNetwork;
import com.example.blin.benlib.BenUtil;

import java.io.File;
import java.util.ArrayList;


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
    public void ShowDirect(View v)
    {
        View view1;

        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view1 = inflater.inflate(R.layout.listfilelayout, null);

        LinearLayout view2;
        view2= (LinearLayout) view1.findViewById(R.id.Dirview);
        ArrayList<File> s1= new ArrayList<File>();;
        BenFileIO A1=new BenFileIO();
        StringBuilder SB=new StringBuilder();
        //getting SDcard root path
        s1=A1.getDirList(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
        /*TextView textView = new TextView(this);
        textView.setText("722222");
        textView.setTextColor(Color.parseColor("#FF0000"));
        textView.setPadding(5, 5, 5, 5);
        view2.addView(textView);*/
          for (int i = 0; i < s1.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(s1.get(i).getName());
            textView.setPadding(5, 5, 5, 5);


            if (s1.get(i).isDirectory()) {
                textView.setTextColor(Color.parseColor("#FF0000"));
            }
            view2.addView(textView);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("2222");
        builder.setTitle("提示");
        builder.setView(view1);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();


        /*for (int i = 0; i < s1.size(); i++) {
            SB.append(s1.get(i).getName()+"\n");
        }
        TextView A3 = (TextView) findViewById(R.id.textView2);
        A3.setText(SB.toString());*/

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
