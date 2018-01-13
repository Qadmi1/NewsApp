package com.example.appty.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by appty on 12/01/18.
 */

public class NetworkQueryRequest {


    // This method fetches the data from the API and return a List containing the desired data.
    public static List<NewsItem> fetchDataFromAPI(String stringURL){
        String jsonResponse;
        URL url = createURL(stringURL);

        jsonResponse = makeHTTPRequest(url);


        return extractNewsFromJson(jsonResponse);
    }
    // This method transform the passed URL from String object type to URL.
    private static URL createURL(String stringURL)
    {
        URL url= null;

        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    // This method takes a URL and return a JSon Response in String object type.
    private static String makeHTTPRequest(URL url)  {
        String jsonResponse = "";
        InputStream inputStream;
        HttpURLConnection httpURLConnection;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200)
            {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = extractJSONFromInputStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }
    // This method takes the Response as InputStream and transform it into String.
    private static String extractJSONFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null)
            {
                builder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return builder.toString();
    }

    // This method takes the JsonResponse as String and extract the desired data and add it to a
    // List of NewsItem type
    private static List<NewsItem> extractNewsFromJson(String jsonResponseString)
    {
        List<NewsItem> news = new ArrayList<>();

        try {
            JSONObject baseObject = new JSONObject(jsonResponseString);

                JSONObject response = baseObject.getJSONObject("response");

            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i<results.length(); i++)
            {
                JSONObject innerObject = results.getJSONObject(i);

                String section = innerObject.getString("sectionName");

                String articleTitle = innerObject.getString("webTitle");

                String webPageUrl = innerObject.getString("webUrl");

                JSONArray tags = innerObject.getJSONArray("tags");

                JSONObject tagsInnerObject = tags.getJSONObject(0);

                String authorName = tagsInnerObject.getString("webTitle");

                news.add(new NewsItem(section, articleTitle, webPageUrl, authorName));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }
}
