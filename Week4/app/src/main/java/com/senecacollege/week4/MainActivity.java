package com.senecacollege.week4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int redValue = 255;
    private int blueValue = 255;
    private int greenValue = 255;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSubmit;
    private TextView textViewForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                handleSeekBarChanges(seekBar.getId(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        redSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);

        //form
        editTextUsername = findViewById(R.id.userNameEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        buttonSubmit = findViewById(R.id.submitButton);
        textViewForm = findViewById(R.id.formTextView);
    }

    private void handleSeekBarChanges(int seekBarId, int progress) {
        if (seekBarId == R.id.redSeekBar) {
            redValue = progress;
        } else if (seekBarId == R.id.blueSeekBar) {
            blueValue = progress;
        } else if (seekBarId == R.id.greenSeekBar) {
            greenValue = progress;
        }
        setBackGroundColour();
    }

    private void setBackGroundColour() {
        int color = android.graphics.Color.rgb(redValue, greenValue, blueValue);

        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundColor(color);
    }

    public void submitForm(View view) {
        // Fetch the values from the EditText fields
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Update the TextView
        String displayText = "Username: " + username + ". Password: " + password;
        textViewForm.setText(displayText);
    }
}