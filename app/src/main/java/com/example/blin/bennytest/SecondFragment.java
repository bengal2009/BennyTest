package com.example.blin.bennytest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_second, container, false);
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
