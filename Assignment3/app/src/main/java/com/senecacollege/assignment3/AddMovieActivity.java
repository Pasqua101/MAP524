package com.senecacollege.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMovieActivity extends AppCompatActivity {

    private Button submitButton;
    private Button cancelButton;
    private EditText titleEditText;
    private EditText yearEditText;
    private EditText imdbIdEditText;
    private EditText typeEditText;
    private EditText posterUrlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);


        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);
        titleEditText = findViewById(R.id.title);
        yearEditText = findViewById(R.id.year);
        imdbIdEditText = findViewById(R.id.imdbId);
        typeEditText = findViewById(R.id.type);
        posterUrlEditText = findViewById(R.id.poster);

        submitButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString().trim();
            String yearString = yearEditText.getText().toString().trim();
            String imdbId = imdbIdEditText.getText().toString().trim();
            String type = typeEditText.getText().toString().trim();
            String posterUrl = posterUrlEditText.getText().toString().trim();

            if (!title.isEmpty()
                    && !yearString.isEmpty()
                    && !imdbId.isEmpty()
                    && !type.isEmpty()
                    && !posterUrl.isEmpty()) {

                int year = Integer.parseInt(yearString);
                MovieModel movie = new MovieModel(title, year, imdbId, type, posterUrl);

                MainActivity.addMovie(movie);

                finish();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(AddMovieActivity.this);

                builder.setTitle("Error");
                builder.setMessage("Please fill all fields");

                builder.setNeutralButton("OK", (DialogInterface.OnClickListener) (dialog, which) ->{
                    dialog.dismiss();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        cancelButton.setOnClickListener(view ->{
            finish();
        });
    }


}