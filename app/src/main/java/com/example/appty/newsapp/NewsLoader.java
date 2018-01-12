package com.example.appty.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by appty on 12/01/18.
 */

public class NewsLoader extends android.content.AsyncTaskLoader<NewsItem> {

    private String GUARDIAN_REQUEST_URL;

    public NewsLoader(Context context, String passedURL) {
        super(context);
        GUARDIAN_REQUEST_URL = passedURL;
    }

    @Override
    protected void onStartLoading() {
        onForceLoad();
    }

    @Override
    public NewsItem loadInBackground() {
        return null;
    }
}
