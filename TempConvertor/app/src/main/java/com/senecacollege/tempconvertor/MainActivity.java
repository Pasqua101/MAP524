package com.senecacollege.tempconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText inputEditText;
    TextView outputTextView;
    Button celciusButton;
    Button fahrenheitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputTempEditText);
        outputTextView = findViewById(R.id.convertedTempTextView);
        celciusButton = findViewById(R.id.CelciusButton);
        fahrenheitButton = findViewById(R.id.FahrenheitButton);

        celciusButton.setOnClickListener(view ->{
            String input = String.valueOf(inputEditText.getText());
            double inputTemp = Double.parseDouble(input);
            double convertedTemp = (inputTemp * 1.8) + 32;

            outputTextView.setText(String.valueOf(convertedTemp));
        });

        fahrenheitButton.setOnClickListener(view -> {
            String input = String.valueOf(inputEditText.getText());
            double inputTemp = Double.parseDouble(input);
            double convertedTemp = (inputTemp - 32) * 5/9;

            outputTextView.setText(String.valueOf(convertedTemp)); //Could also use outputTextView.getText().toString();

        });

    }
}