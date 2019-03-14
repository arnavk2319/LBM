package com.single.arnavkaushal.book_client_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.single.arnavkaushal.book_client_app.Activity.SummaryActivity;
import com.single.arnavkaushal.book_client_app.Model.Movie;
import com.single.arnavkaushal.book_client_app.R;
import com.single.arnavkaushal.book_client_app.Viewholder.TabOneViewholder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TabOneAdapter extends RecyclerView.Adapter<TabOneViewholder> {
    private List<Movie> movies;
    private int rowLayout;
    Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public TabOneAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    @NonNull
    @Override
    public TabOneViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout,parent,false);
        return new TabOneViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabOneViewholder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());

        holder.movieImage.buildDrawingCache();
        final Bitmap bitmap = holder.movieImage.getDrawingCache();

        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SummaryActivity.class);
                intent.putExtra("MOVIE_TITLE",movies.get(position).getTitle());
                intent.putExtra("MOVIE_RELEASE_DATE",movies.get(position).getReleaseDate());
                intent.putExtra("MOVIE_DESCRIPTION",movies.get(position).getOverview());
                intent.putExtra("MOVIE_RATING",movies.get(position).getVoteAverage().toString());
                intent.putExtra("MOVIE_ORIGINAL_TITLE",movies.get(position).getOriginalTitle());
                intent.putExtra("MOVIE_ORIGINAL_LANGUAGE",movies.get(position).getOriginalLanguage());
//                intent.putExtra("MOVIE_IMAGE",bitmap);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
