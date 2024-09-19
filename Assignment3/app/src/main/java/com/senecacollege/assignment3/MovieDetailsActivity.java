package com.senecacollege.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView yearTextView;
    private TextView imdbIdTextView;
    private TextView typeTextView;
    private ImageView posterImageView;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MovieModel movie = getIntent().getParcelableExtra("movie");

        titleTextView = findViewById(R.id.title);
        yearTextView = findViewById(R.id.year);
        imdbIdTextView = findViewById(R.id.imdbId);
        typeTextView = findViewById(R.id.type);
        posterImageView = findViewById(R.id.poster);
        goBackButton = findViewById(R.id.goBackButton);

        titleTextView.setText("Title: " + movie.getTitle());
        yearTextView.setText("Year of Release: " + String.valueOf(movie.getYear()));
        imdbIdTextView.setText("IMDb ID: " +movie.getImdbID());
        typeTextView.setText("Type of Movie: " + movie.getType());

        //Note: In order to use Picasso, must add in a dependency in build.gradle, and add in a line in the manifest file for internet
        if (!TextUtils.isEmpty(movie.getPosterUrl())){
            Picasso.get().load(movie.getPosterUrl()).resize(800, 1200).into(posterImageView);
        }

        goBackButton.setOnClickListener(view -> {
            finish();
        });
    }
}