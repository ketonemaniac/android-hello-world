package com.sqisland.android.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
    }

    public void onShowButtonClicked(View view) {
        TextView helloText = (TextView) findViewById(R.id.helloText);
        helloText.setVisibility(View.VISIBLE);
    }

}