package com.senecacollege.phonetextapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    String strPhoneNumber; // To be used with EditText
    private Button callNumberButton;
    private Button textNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        callNumberButton = findViewById(R.id.callNumberButton);
        textNumberButton = findViewById(R.id.textNumberButton);

        callNumberButton.setOnClickListener(view -> {
            strPhoneNumber = phoneNumberEditText.getText().toString();

            if(strPhoneNumber.trim().length() > 0){
                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else{
                    String dial = "tel:" + strPhoneNumber;
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                    startActivity(intent);
                }
            } else{
                Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            }
        });

        textNumberButton.setOnClickListener(view -> {
            strPhoneNumber = phoneNumberEditText.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", strPhoneNumber, null));
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission was granted
            String dial = "tel:" + strPhoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        } else {
            // Permission was denied
            Toast.makeText(MainActivity.this, "Please allow permissions", Toast.LENGTH_SHORT).show();
        }
    }
}