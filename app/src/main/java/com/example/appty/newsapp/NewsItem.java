package com.example.appty.newsapp;

/**
 * Created by appty on 10/01/18.
 */

/**
 * An {@link NewsItem} object contains information related to a single NewsItem.
 */
public class NewsItem {

    /**
     * The section of the news page
     */
    private String section;

    /**
     * The articleTitle of the news page
     */
    private String articleTitle;

    /**
     * The URL of the news page
     */
    private String url;

    /**
     * The name of the author of the news page
     */
    private String authorName;

    /**
     * The date of the news
     */
    private String date;


    /**
     * Constructs a new {@link NewsItem} object.
     *
     * @param section      is the section of the news.
     * @param articleTitle is the title of the article.
     * @param url          is the website URL to find more details about the news.
     * @param authorName   is the name of the author who created the article/news.
     * @param date         is the date of the news.
     */
    public NewsItem(String section, String articleTitle, String url, String authorName,
                    String date) {
        this.section = section;
        this.articleTitle = articleTitle;
        this.url = url;
        this.authorName = authorName;
        this.date = date;
    }

    /**
     * Getter for the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Getter for the articleTitle
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * Getter for the web page is url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Getter for the web page is url
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Getter for the web page is url
     */
    public String getDate() {
        return date;
    }
}
