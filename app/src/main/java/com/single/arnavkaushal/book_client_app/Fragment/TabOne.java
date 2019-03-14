package com.single.arnavkaushal.book_client_app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.single.arnavkaushal.book_client_app.Activity.MainActivity;
import com.single.arnavkaushal.book_client_app.Adapter.TabOneAdapter;
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

public class TabOne extends Fragment {
    int item_count = 100;
    RecyclerView tabOneRecyclerView;
    TabOneAdapter tabOneAdapter;
    TextView tabOneTextView;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tabonefragment,container,false);

//        tabOneTextView = view.findViewById(R.id.tabOneTextView);
//        tabOneAdapter = new TabOneAdapter(getContext(),item_count);
        tabOneRecyclerView = view.findViewById(R.id.tabOneRecyclerView);

//        tabOneRecyclerView.setAdapter(tabOneAdapter);
        tabOneRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        connectAndGetApiData();

        return view;
    }

    public void connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.getTopRatedMovies("d22aa15d1e8ed4334113bc95aba33be5");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                tabOneRecyclerView.setAdapter(new TabOneAdapter(movies, R.layout.tab_one_view, getContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

}
