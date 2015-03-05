package com.example.blin.bennytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by blin on 2015/3/5.
 */
public class FourthFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void InitListview(View V) {
        ListView A3= (ListView) V.findViewById(R.id.CustListView);
//        ArrayList<String> A1 = new ArrayList<String>();
        final String[] mTestArray;
        mTestArray=getResources().getStringArray(R.array.TestStringArray);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,mTestArray);
        A3.setAdapter(adapter);
        A3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Toast.makeText(getActivity(), "Item:" + mTestArray[position].toString() ,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_fourth, container, false);
        InitListview(view);

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