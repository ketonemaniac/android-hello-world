package com.sqisland.android.hello.io;

import android.os.AsyncTask;
import android.os.Handler;

import com.sqisland.android.hello.MainActivity;

/**
 * Async task to retrieve feed.
 */
public class RetrieveFeedTask extends AsyncTask<Object, Void, IotdHandler> {

    /**
     *
     * @param params [0] the UI handler, [1] the MainActivity
     * @return
     */
    @Override
    protected IotdHandler doInBackground(final Object ... params) {
        final IotdHandler feedHandler = new IotdHandler();
        feedHandler.processFeed();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Handler)params[0]).post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)params[1]).postDisplay(feedHandler);
            }
        });
        return feedHandler;
    }


}
