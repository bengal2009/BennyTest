package com.example.blin.bennytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class TestBlank extends ActionBarActivity {
   private  ListView A3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_blank);
        A3= (ListView) findViewById(R.id.listView1);
        InitListview();
        A3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "Item:" + position,
                        Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void InitListview() {
        ArrayList<String> A1 = new ArrayList<String>();
        A1.add("11111");
        A1.add("21111");
        A1.add("31111");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, A1);
        A3.setAdapter(adapter);
        // ListView Item Click Listener


    }


public void BackIntent(View v)
{
//    Intent intent = getIntent();
    Intent intent1 = new Intent(getApplicationContext(),  MainActivity.class);
    startActivity(intent1);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_blank, menu);
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
