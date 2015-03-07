package com.example.blin.bennytest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
Context mcontext;
    GestureDetector gestureDetector;
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

        InitAnim(A2);
        //Set Preference

        SharedPreferences SP=getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = SP.edit();
        if(SP==null) {

            SP.edit()
                    .putString("Name", "Benny")
                    .putString("Addr", "Pudong")
                    .commit();

            Log.i("MainActivity","SP Create!");
        }
        else {

            Log.i("MainActivity","SP Read!"+"Name"+SP.getString("Name",""));
        }
     //Gesture Test
        InitGesture();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    public void  InitGesture(){
        gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                    float distanceY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                if(velocityX>0){
                   // viewFlipper.showNext();
                    Toast.makeText(getApplicationContext(), "Right",
                            Toast.LENGTH_SHORT).show();
                }else {
                    //viewFlipper.showPrevious();
                    Toast.makeText(getApplicationContext(), "Left",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }
public  void InitAnim(TextView A2)
{
    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
    A2.startAnimation(animation2);
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
