package com.senecacollege.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button increaseButton;
    Button decreaseButton;
    TextView counterTextView;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        counterTextView = findViewById(R.id.counter);

        counter = 0;

        increaseButton.setOnClickListener(view -> {
            counter += 1;
            counterTextView.setText(String.valueOf(counter));
        });

        decreaseButton.setOnClickListener(view -> {
            counter -= 1;
            counterTextView.setText(String.valueOf(counter));
        });
    }
}