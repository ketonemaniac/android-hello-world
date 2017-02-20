package com.sqisland.android.hello.listactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sqisland.android.hello.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Somewhat like ViewModel in C#
 */

public class ListAdapter extends BaseAdapter {

    private List<String> myList = new ArrayList<String>();

    public ListAdapter() {
        myList.add("Headline 1");
        myList.add("Headline 2");
        myList.add("Headline 3");
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.adaptor_list_item, parent, false);
        }
        TextView view = (TextView) convertView.findViewById(R.id.subCaption);
        view.setText(myList.get(position));

        return convertView;
    }

    public void addHeadline(String headline) {
        myList.add(headline);
    }
}
