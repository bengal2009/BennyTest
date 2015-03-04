package com.example.blin.bennytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView A2=(TextView) findViewById(R.id.textview1);
        mcontext=this;
        ArrayList<String> A1=new ArrayList<String>();
        A1.add("11111");
        A1.add("21111");
        A1.add("31111");
        StringBuilder SB=new StringBuilder();

        for(String i:A1) {
            SB.append(i+"\n");

        }
        A2.setText(SB.toString());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//Test Fragment
    public void TestClick(View v)
    {
        Intent intent1 = new Intent(getApplicationContext(),  TestBlank.class);
//        intent.putExtra("bundle", "Second");
      	startActivity(intent1);


        Toast.makeText(getApplicationContext(), "Test",
                Toast.LENGTH_SHORT).show();
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
