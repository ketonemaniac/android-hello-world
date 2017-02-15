package com.sqisland.android.hello;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.android.hello.io.IotdHandler;
import com.sqisland.android.hello.io.RetrieveFeedTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity {

    Handler handler;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        doFetch();
    }

    private void doFetch() {
        dialog = ProgressDialog.show(this, "Loading", "Loading News...");

        // Cannot do internet connections on main thread!! Sprawn one thread to do it.
        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute(handler, this);
        // can't do this since this will BLOCK the UI thread (and no repaint will result)
        // This is why "handler" is used -- pass it to AsyncTask
        // for UI update calls to be put in UI thread.
        // IotdHandler handler = task.get(10, TimeUnit.SECONDS);
    }

    public void onShowButtonClicked(View view) {
        TextView helloText = (TextView) findViewById(R.id.helloText);
        helloText.setVisibility(View.VISIBLE);
    }

    public void onRefreshButtonClicked(View view) {
        doFetch();
    }



    public void postDisplay(IotdHandler iotdHandler) {
        resetDisplay(iotdHandler.getTitle(), iotdHandler.getDate(), iotdHandler.getImage(), iotdHandler.getDescription());
        dialog.dismiss();
        Toast.makeText(this, "Feed Updated", Toast.LENGTH_SHORT).show();
    }

    private void resetDisplay(String title, String date, Bitmap image, String description) {
        TextView titleView = (TextView) findViewById(R.id.newsTitle);
        titleView.setText(title);
        TextView dateView = (TextView) findViewById(R.id.newsDate);
        dateView.setText(date);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(image);
        TextView descriptionView = (TextView) findViewById(R.id.newsContent);
        descriptionView.setText(description);
    }

}