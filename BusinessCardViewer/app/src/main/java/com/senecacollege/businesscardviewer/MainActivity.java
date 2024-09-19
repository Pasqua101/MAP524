package com.senecacollege.businesscardviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String name =
            "com.senecacollege.businesscardviewer.extra.MESSAGE";
    public static final String phone =
            "com.senecacollege.businesscardviewer.extra.MESSAGE";
    public static final String email =
            "com.senecacollege.businesscardviewer.extra.MESSAGE";
    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        nextButton = findViewById(R.id.next);

        nextButton.setOnClickListener(view ->{

            String name = String.valueOf(nameEditText.getText());
            String phone = String.valueOf(phoneEditText.getText());
            String email = String.valueOf(emailEditText.getText());

            Intent intent = new Intent(this, BusinessCardActivity.class);
            intent.putExtra(name, name);
            startActivity(intent);

        });

    }
}