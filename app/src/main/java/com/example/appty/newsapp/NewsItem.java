package com.example.appty.newsapp;

/**
 * Created by appty on 10/01/18.
 */

/**
 * An {@link NewsItem} object contains information related to a single NewsItem.
 */
public class NewsItem {

    /** The section of the news page */
    private String section;
    /** The articleTitle of the news page */
    private String articleTitle;

    /**
     * Constructs a new {@link NewsItem} object.
     *
     * @param section is the magnitude (size) of the earthquake
     * @param articleTitle is the location where the earthquake happened
//     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
//     *                           earthquake happened
//     * @param url is the website URL to find more details about the earthquake
     */
    public NewsItem(String section, String articleTitle) {
        this.section = section;
        this.articleTitle = articleTitle;
    }

    /** Getter for the section */
    public String getSection() {
        return section;
    }

    /** Getter for the articleTitle */
    public String getArticleTitle() {
        return articleTitle;
    }
}
