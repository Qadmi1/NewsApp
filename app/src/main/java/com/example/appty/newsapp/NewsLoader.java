package com.example.appty.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by appty on 12/01/18.
 */

public class NewsLoader extends AsyncTaskLoader<NewsItem> {


    public NewsLoader(Context context) {
        super(context);
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
