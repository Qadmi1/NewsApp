package com.example.appty.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Find the current new item/object
                NewsItem currentNews = adapter.getItem(i);

                // Convert the String URL into a URI object
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create explicit intent to open the news on a web page
                Intent openWebPage = new Intent(Intent.ACTION_VIEW, newsUri);

                startActivity(openWebPage);
            }
        });

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
