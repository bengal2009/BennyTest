package com.example.blin.bennytest.SQLTEST;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.blin.benlib.BenSqllite;
import com.example.blin.bennytest.R;

public class Addrecord extends ActionBarActivity {
    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecord);
    }

public void backmain(View v)
{
    subjectEditText = (EditText) findViewById(R.id.subject_edittext);
    descEditText = (EditText) findViewById(R.id.description_edittext);

    addTodoBtn = (Button) findViewById(R.id.add_record);
    Log.i("SQL","Click!");
BenSqllite.DBManager dbManager = new BenSqllite.DBManager(this);
    dbManager.open();
    final String name = subjectEditText.getText().toString();
    final String desc = descEditText.getText().toString();
    Log.i("SQL","Over!");
    dbManager.insert(name, desc);

  /*  Intent main = new Intent(this, SqlMain.class)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    startActivity(main);*/
//    addTodoBtn.setOnClickListener(this);
   Intent main = new Intent(this, SqlMain.class)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    startActivity(main);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addrecord, menu);
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
