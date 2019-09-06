package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.popularmoviesstage1.Utils.NetworksUtils;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView posterImg;
    TextView titleTv;
    TextView releaseDate;
    RatingBar ratingRb;
    TextView synposisTv;
    public static final String POSTER_IMAGE_PATH = "posterImg";
    public static final String TITLE = "titleTv";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String RATING = "ratingRb";
    public static final String SYNPOSIS = "synposisTv";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        posterImg = findViewById(R.id.image_iv);
        titleTv = findViewById(R.id.title_tv);
        releaseDate = findViewById(R.id.release_date_tv);
        ratingRb = findViewById(R.id.ratings_rb);
        synposisTv = findViewById(R.id.synopsis_tv);
        Intent intent = getIntent();
        if (intent != null) {
            titleTv.setText(intent.getStringExtra(TITLE));
            releaseDate.setText(intent.getStringExtra(RELEASE_DATE));
            ratingRb.setRating((float)intent.getDoubleExtra(RATING,0));
            synposisTv.setText(intent.getStringExtra(SYNPOSIS));
            Picasso.get().load(Uri.parse(NetworksUtils.IMAGE_BASE_URL + intent.getStringExtra(POSTER_IMAGE_PATH))).fit().into(posterImg);
        }


    }
}
