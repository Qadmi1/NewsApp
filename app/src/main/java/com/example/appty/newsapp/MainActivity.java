package com.example.appty.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {

    private String GUARDIAN_REQUEST_URL ="http://content.guardianapis.com/search?api-key=test";
    private NewsAdapter adapter;
    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new NewsAdapter(this, new ArrayList<NewsItem>());

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }


    @Override
    public Loader<List<NewsItem>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> newsItemList) {
        adapter.clear();
        adapter.addAll(newsItemList);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        adapter.clear();
    }
}
