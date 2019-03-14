package com.single.arnavkaushal.book_client_app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.single.arnavkaushal.book_client_app.Adapter.BookAdapter;
import com.single.arnavkaushal.book_client_app.BookClient;
import com.single.arnavkaushal.book_client_app.Model.Book;
import com.single.arnavkaushal.book_client_app.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TabTwo extends Fragment {
    ListView lv;
    BookAdapter bookAdapter;
    private BookClient bookClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tabtwofragment,container,false);

        lv = (ListView)view.findViewById(R.id.listView);
        ArrayList<Book> aBooks = new ArrayList<>();
        bookAdapter = new BookAdapter(getContext(),aBooks);
        lv.setAdapter(bookAdapter);
        String classic_query = "classic";
        fetchBooks(classic_query);

        return view;
    }

    private void fetchBooks(String query)
    {
        bookClient = new BookClient();
        bookClient.getBooks(query, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try
                {
                    JSONArray docs = null;
                    if(response!=null)
                    {
                        docs = response.getJSONArray("docs");
                        final ArrayList<Book> books = Book.fromJSONArray(docs);
                        bookAdapter.clear();
                        for(Book book : books) {
                            bookAdapter.add(book);
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
