package com.single.arnavkaushal.book_client_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.single.arnavkaushal.book_client_app.Adapter.MovieAdapter;
import com.single.arnavkaushal.book_client_app.Model.Movie;
import com.single.arnavkaushal.book_client_app.Model.MovieResponse;
import com.single.arnavkaushal.book_client_app.MovieApiService;
import com.single.arnavkaushal.book_client_app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMovieActivity extends AppCompatActivity {

    ListView lv;
    MovieAdapter movieAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        lv = (ListView)findViewById(R.id.listView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ArrayList<Movie> aBooks = new ArrayList<>();
//        movieAdapter = new MovieAdapter(this,aBooks);
//        lv.setAdapter(movieAdapter);

    }

    public void connectAndGetApiData(String movieQuery) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.searchMovieNames("d22aa15d1e8ed4334113bc95aba33be5",movieQuery);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                lv.setAdapter(new MovieAdapter(getApplicationContext(),movies));
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_list,menu);
        final MenuItem item = menu.findItem(R.id.movie_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onQueryTextSubmit(String query) {
                connectAndGetApiData(query);
                searchView.clearFocus();
                searchView.setQuery(" ",false);
                searchView.setIconified(true);
                searchView.setBackgroundColor(R.color.colorAccent);
                item.collapseActionView();
                SearchMovieActivity.this.setTitle(query);
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
