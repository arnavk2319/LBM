package com.single.arnavkaushal.book_client_app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.single.arnavkaushal.book_client_app.R;

public class SummaryActivity extends AppCompatActivity {
    TextView title_summary, original_title_summary, original_language_summary, date_summary, description_summary, rating_summary;
    ImageView movie_image_summary;
    Bitmap bitmap;
    Toolbar toolbar;
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342//";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        title_summary = findViewById(R.id.title_summary);
        original_title_summary = findViewById(R.id.original_title_summary);
        original_language_summary = findViewById(R.id.original_language_summary);
        date_summary = findViewById(R.id.date_summary);
        description_summary = findViewById(R.id.description_summary);
        rating_summary = findViewById(R.id.rating_summary);
        movie_image_summary = findViewById(R.id.movie_image_summary);

        bitmap = (Bitmap) getIntent().getParcelableExtra("MOVIE_IMAGE");

        String movieTitle = getIntent().getStringExtra("MOVIE_TITLE");
        String movieOriginalTitle = getIntent().getStringExtra("MOVIE_ORIGINAL_TITLE");
        String movieOriginalLanguage = getIntent().getStringExtra("MOVIE_ORIGINAL_LANGUAGE");
        String releaseDate = getIntent().getStringExtra("MOVIE_RELEASE_DATE");
        String movieDescription = getIntent().getStringExtra("MOVIE_DESCRIPTION");
        String movieRating = getIntent().getStringExtra("MOVIE_RATING");

        movie_image_summary.setImageBitmap(bitmap);
        title_summary.setText(movieTitle);
        original_title_summary.setText("Original Title : " + " " + movieOriginalTitle);
        original_language_summary.setText(movieOriginalLanguage);
        date_summary.setText(releaseDate);
        description_summary.setText(movieDescription);
        rating_summary.setText(movieRating);


//        if(getIntent().hasExtra("byteArray")) {
//            Bitmap b = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//            movie_image_summary.setImageBitmap(b);
//        }
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
