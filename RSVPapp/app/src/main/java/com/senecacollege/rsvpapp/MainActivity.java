package com.senecacollege.rsvpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    RadioGroup rsvpRadioGroup;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        rsvpRadioGroup = findViewById(R.id.rsvpSelection);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(view -> {
            int selectedRadioButtonId = rsvpRadioGroup.getCheckedRadioButtonId();
            String rsvpStatus = "";
            if(selectedRadioButtonId == R.id.attendingRadio){
                rsvpStatus = "Attending";
            }
            else if (selectedRadioButtonId == R.id.maybeRadio){
                rsvpStatus = "Maybe";
            }
            else if (selectedRadioButtonId == R.id.notAttendingRadio){
                rsvpStatus = "Not Attending";
            }

            if (nameEditText.getText() == null || selectedRadioButtonId == -1){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                String responseMessage = "Thank you " + nameEditText.getText().toString() + ", your response has been recorded as " + rsvpStatus;
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, responseMessage, Toast.LENGTH_SHORT);
                toast.show();

                nameEditText.setText("");
                rsvpRadioGroup.clearCheck();
            }
        });

    }
}