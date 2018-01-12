package com.example.appty.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by appty on 10/01/18.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {


    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
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

        // Find the section and display it.
        TextView section = newsItemView.findViewById(R.id.section);
        section.setText(currentNewsItem.getSection());

        // Find the article title and display it.
        TextView articleTitle = newsItemView.findViewById(R.id.title);
        articleTitle.setText(currentNewsItem.getArticleTitle());


        return super.getView(position, convertView, parent);
    }
}
