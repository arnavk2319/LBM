package com.single.arnavkaushal.book_client_app.Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.single.arnavkaushal.book_client_app.R;

public class TabOneViewholder extends RecyclerView.ViewHolder {

    public LinearLayout moviesLayout;
    public TextView movieTitle;
    public TextView data;
    public TextView movieDescription;
    public TextView rating;
    public ImageView movieImage;

    public TabOneViewholder(View v) {
        super(v);

        moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
        movieImage = (ImageView) v.findViewById(R.id.movie_image);
        movieTitle = (TextView) v.findViewById(R.id.title);
        data = (TextView) v.findViewById(R.id.date);
        movieDescription = (TextView) v.findViewById(R.id.description);
        rating = (TextView) v.findViewById(R.id.rating);
    }


}
