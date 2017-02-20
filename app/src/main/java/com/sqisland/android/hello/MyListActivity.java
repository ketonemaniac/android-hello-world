package com.sqisland.android.hello;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.sqisland.android.hello.listactivity.ListAdapter;

public class MyListActivity extends Activity {

    public static final int NEWS_RESULT_CODE = 1;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar (use of create opetions menu, otherwise will not be shown!!)
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // must be after remove title bar
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.activity_list_view);
        adapter = new ListAdapter();
        listView.setAdapter(adapter);

        // popup memu enabler
        Button btn = (Button) findViewById(R.id.menuBtn);
        registerForContextMenu(btn);
    }

    // MENUS, two ways for doing the same thing.

    // default options menu from the title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.news_menu,menu);
        return true;
    }

// popup menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
//        menu.add(0, v.getId(), 0, "Action 1");
//        menu.add(0, v.getId(), 0, "Action 2");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.news_menu,menu);
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.read_news_menu_item) {
            Intent intent = new Intent(this, MainActivity.class);
            // startActivity(intent);   // for no activity result
            startActivityForResult(intent, NEWS_RESULT_CODE);
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEWS_RESULT_CODE) {
            if(resultCode == RESULT_OK) {
                String headline = data.getStringExtra("headline");
                adapter.addHeadline(headline);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
