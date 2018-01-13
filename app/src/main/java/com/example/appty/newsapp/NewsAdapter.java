package com.example.appty.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by appty on 10/01/18.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context      of the app
     * @param newsItemList is the list of earthquakes, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<NewsItem> newsItemList) {
        super(context, 0, newsItemList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /**
         * Check if there is an existing View that will be returned as an item for the ListView,
         * if not, then inflate a news_item layout.
         *
         */
        View newsItemView = convertView;
        if (newsItemView == null) {
            newsItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // Find the current NewsItem.
        NewsItem currentNewsItem = getItem(position);

        assert currentNewsItem != null;

        // Find the section and display it.
        TextView section = newsItemView.findViewById(R.id.section);
        section.setText(currentNewsItem.getSection());

        // Find the article title and display it.
        TextView articleTitle = newsItemView.findViewById(R.id.article);
        articleTitle.setText(currentNewsItem.getArticleTitle());

        // Find the author of the news and display him/her.
        TextView authorName = newsItemView.findViewById(R.id.author);
        authorName.setText(currentNewsItem.getAuthorName());

        return newsItemView;
    }
}
