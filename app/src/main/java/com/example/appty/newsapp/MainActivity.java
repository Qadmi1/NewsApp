package com.example.appty.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {

    private NewsAdapter adapter;
    private static final String  GUARDIAN_REQUEST_URL ="http://content.guardianapis.com/search?api-key=test";
    private static final int LOADER_ID = 1;
    private TextView emptyHandler;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pass the context and an empty List that will be populated later
        adapter = new NewsAdapter(this, new ArrayList<NewsItem>());
        // Find the ListView
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //Find the empty handler TextView
        emptyHandler = findViewById(R.id.empty_handler);
        // Set the empty view for the ListView to be emptyHandler TextView
        listView.setEmptyView(emptyHandler);
            // Create an onItemClickListener for the ListView
            listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Find the current new item/object
                NewsItem currentNews = adapter.getItem(i);

                // Convert the String URL into a URI object
                assert currentNews != null;
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create explicit intent to open the news on a web page
                Intent openWebPage = new Intent(Intent.ACTION_VIEW, newsUri);

                startActivity(openWebPage);
            }
        });

        // Check if there is a connection
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // Make sure there is a connection before fetching the data
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // Create a reference to the LoaderManager
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader to create a new loader or use the existing one.
            loaderManager.initLoader(LOADER_ID, null, this);
        }
        else {

            // Find the ProgressBar and hide it after displaying then empty handler view
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.INVISIBLE);
            // If there is no internet show a message to inform the user
            emptyHandler.setText(R.string.no_internet);

        }
        }


    @Override
    public Loader<List<NewsItem>> onCreateLoader(int i, Bundle bundle) {
        // Create an anonymous object of our custom Loader that would make the network
        // request on a background thread and return it in a List of NewsItem object type
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> newsItemList) {
        // First clear the adapter
        adapter.clear();
        // if there is no items in the ListView show a message that says so.
        emptyHandler.setText(R.string.empty);
        // Find the ProgressBar and hide it after the load is complete
        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.INVISIBLE);
        // Make sure that that the passed List is not null or empty
        if (newsItemList != null && !newsItemList.isEmpty())
        adapter.addAll(newsItemList);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        adapter.clear();
    }
}
