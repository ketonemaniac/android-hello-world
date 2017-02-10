package com.sqisland.android.hello.io;

import android.os.AsyncTask;
import android.os.Handler;

import com.sqisland.android.hello.MainActivity;

/**
 * Async task to retrieve feed.
 */
public class RetrieveFeedTask extends AsyncTask<Object, Void, IotdHandler> {

    @Override
    protected IotdHandler doInBackground(final Object ... params) {
        final IotdHandler handler = new IotdHandler();
        handler.processFeed();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Handler)params[0]).post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)params[1]).postDisplay(handler);
            }
        });
        return handler;
    }


}
