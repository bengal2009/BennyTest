package com.example.blin.bennytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blin.benlib.FileIO;

import java.util.ArrayList;

/**
 * Created by blin on 2015/3/5.
 */
public class FifthFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       FileIO A1=new FileIO();
        A1.Test();

    }
    public void InitCustomListView(View V1)
    {
        ArrayList<PersonItem> image_details = getListData();
        final ListView lv1 = (ListView) V1.findViewById(R.id.CustListView);
        lv1.setAdapter(new CustListAdp(getActivity(), image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                PersonItem newsData = (PersonItem) o;
                Toast.makeText(getActivity(), "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }

        });
    }
    private ArrayList<PersonItem> getListData() {
        ArrayList<PersonItem> results = new ArrayList<PersonItem>();
        PersonItem PersonData = new PersonItem();
        PersonData.setFamilyName("Dance of Democracy");
        PersonData.setGender("Female");
        PersonData.setBirthday("May 26, 2013, 13:35");
        results.add(PersonData);

        PersonData = new PersonItem();
        PersonData.setFamilyName("Benny");
        PersonData.setGender("Male");
        PersonData.setBirthday("May 26, 2013, 13:35");
        results.add(PersonData);
        return  results;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_fifth, container, false);
        InitCustomListView(view);
        Log.i("Benny","Customs List View Created!");

/*
        TextView text = (TextView)view.findViewById(R.id.detailsText);

        Bundle element = this.getArguments();
        int position = element.getInt("position");

        String[] elementDetails = getActivity().getResources().getStringArray(R.array.ElementsInfoArray);
        text.setText(elementDetails[position]);
*/

        return view;
    }


}