package com.single.arnavkaushal.book_client_app;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BookClient
{
    private static final String API_URL = "http://openlibrary.org/";
    private AsyncHttpClient client;

    public BookClient()
    {
        this.client = new AsyncHttpClient();
    }
    private String getUrl(String relativeURL)
    {
        return API_URL + relativeURL;
    }

    public void getBooks(final String query, JsonHttpResponseHandler handler)
    {
        String url = getUrl("search.json?q=");
        try {
            client.get(url + URLEncoder.encode(query,"utf-8"), handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
