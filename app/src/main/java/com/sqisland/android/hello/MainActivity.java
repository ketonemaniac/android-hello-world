package com.sqisland.android.hello;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sqisland.android.hello.io.IotdHandler;
import com.sqisland.android.hello.io.RetrieveFeedTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
        // Cannot do internet connections on main thread!! Sprawn one thread to do it.
        RetrieveFeedTask task = new RetrieveFeedTask();
        try {
            task.execute();
            IotdHandler handler = task.get(10, TimeUnit.SECONDS);
            resetDisplay(handler.getTitle(), handler.getDate(), handler.getImage(), handler.getDescription());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void onShowButtonClicked(View view) {
        TextView helloText = (TextView) findViewById(R.id.helloText);
        helloText.setVisibility(View.VISIBLE);
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