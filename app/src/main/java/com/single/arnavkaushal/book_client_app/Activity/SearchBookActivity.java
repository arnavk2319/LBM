package com.single.arnavkaushal.book_client_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

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

public class SearchBookActivity extends AppCompatActivity {
    ListView lv;
    BookAdapter bookAdapter;
    private BookClient bookClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lv = (ListView)findViewById(R.id.listView_search);
        ArrayList<Book> aBooks = new ArrayList<>();
        bookAdapter = new BookAdapter(this,aBooks);
        lv.setAdapter(bookAdapter);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_list,menu);
        final MenuItem item = menu.findItem(R.id.book_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchBooks(query);
                searchView.clearFocus();
                searchView.setQuery(" ",false);
                searchView.setIconified(true);
                searchView.setBackgroundColor(R.color.colorAccent);
                item.collapseActionView();
                SearchBookActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
