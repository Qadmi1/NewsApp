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

    /** The URL of the news page */
    private String url;


    /**
     * Constructs a new {@link NewsItem} object.
     *
     * @param section is the magnitude (size) of the earthquake
     * @param articleTitle is the location where the earthquake happened
     * @param url is the website URL to find more details about the earthquake
     */
    public NewsItem(String section, String articleTitle, String url) {
        this.section = section;
        this.articleTitle = articleTitle;
        this.url = url;
    }

    /** Getter for the section */
    public String getSection() {
        return section;
    }

    /** Getter for the articleTitle */
    public String getArticleTitle() {
        return articleTitle;
    }

    /** Getter for the web page is url */
    public String getUrl() {
        return url;
    }
}
