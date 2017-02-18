package com.sqisland.android.hello;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import com.sqisland.android.hello.listactivity.ListAdapter;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.activity_list_view);
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);
    }

}
