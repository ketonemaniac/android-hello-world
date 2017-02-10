package com.sqisland.android.hello.io;

import android.os.AsyncTask;

/**
 * Async task to retrieve feed.
 */
public class RetrieveFeedTask extends AsyncTask<String, Void, IotdHandler> {

    @Override
    protected IotdHandler doInBackground(String ... urls) {
        IotdHandler handler = new IotdHandler();
        handler.processFeed();
        return handler;
    }


}
