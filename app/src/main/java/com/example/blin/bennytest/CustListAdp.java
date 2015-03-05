package com.example.blin.bennytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by blin on 2015/3/5.
 */
public class CustListAdp extends BaseAdapter {
    private ArrayList<PersonItem> listData;
    private LayoutInflater layoutInflater;
    public CustListAdp(Context context, ArrayList<PersonItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.NameView = (TextView) convertView.findViewById(R.id.ID_Name);
            holder.GenderView = (TextView) convertView.findViewById(R.id.ID_Gender);
            holder.BirthView= (TextView) convertView.findViewById(R.id.ID_Birt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.NameView.setText(listData.get(position).getFamilyName());
        holder.GenderView.setText("By, " + listData.get(position).getGender());
        holder.BirthView.setText(listData.get(position).getBirthday());

        return convertView;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        TextView NameView;
        TextView GenderView;
        TextView BirthView;
    }
}
