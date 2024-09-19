package com.senecacollege.businesscardviewer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BusinessCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_card);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity);

    }
}
