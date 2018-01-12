package com.example.appty.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by appty on 12/01/18.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    private String GUARDIAN_REQUEST_URL;

    public NewsLoader(Context context, String passedURL) {
        super(context);
        GUARDIAN_REQUEST_URL = passedURL;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<NewsItem> loadInBackground() {
        List<NewsItem> news = NetworkQueryRequest.fetchDataFromAPI(GUARDIAN_REQUEST_URL);
        return news;
    }
}
