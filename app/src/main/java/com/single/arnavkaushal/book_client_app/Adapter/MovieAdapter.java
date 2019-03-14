package com.single.arnavkaushal.book_client_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.single.arnavkaushal.book_client_app.Model.Movie;
import com.single.arnavkaushal.book_client_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie>
{

    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";
    private List<Movie> movies;

    private static class ViewHolder
    {
        public ImageView ivMovieCover;
        public TextView tvMovieTitle;
        public TextView tvMovieDate;
        public TextView tvMovieRating;
        public TextView tvMovieDescription;
    }

    public MovieAdapter(Context context, List<Movie> aMovies)
    {
        super(context,0,aMovies);
        this.movies = aMovies;
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        final Movie movie = getItem(position);

        ViewHolder viewHolder;
//        if(convertView == null)
//        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder.ivMovieCover = (ImageView)convertView.findViewById(R.id.ivMovieCover);
            viewHolder.tvMovieTitle = (TextView)convertView.findViewById(R.id.tvMovieTitle);
            viewHolder.tvMovieDate = (TextView)convertView.findViewById(R.id.tvMovieDate);
            viewHolder.tvMovieRating = convertView.findViewById(R.id.tvMovieRating);
            viewHolder.tvMovieDescription = convertView.findViewById(R.id.tvMovieDescription);

            convertView.setTag(convertView);

//        }
//        else
//        {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        assert movie != null;

        viewHolder.tvMovieDate.setText(movie.getReleaseDate());
        viewHolder.tvMovieTitle.setText(movie.getTitle());
        viewHolder.tvMovieRating.setText(movie.getVoteAverage().toString());
        viewHolder.tvMovieDescription.setText(movie.getOverview());
//        Picasso.with(getContext()).load(Uri.parse(movie.getPosterPath())).error(android.R.drawable.sym_def_app_icon).into(viewHolder.ivMovieCover);
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(getContext())
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(viewHolder.ivMovieCover);


        return convertView;
    }
}
