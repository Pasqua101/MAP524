package com.senecacollege.cameraapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView pictureImageView;
    private Button takePictureButton;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    dispatchTakePictureIntent();
                } else {
                    // handle permission denial with an alert
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureImageView = findViewById(R.id.pictureImageView);
        takePictureButton = findViewById(R.id.cameraButton);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Bundle extras = result.getData().getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        pictureImageView.setImageBitmap(imageBitmap);;
                    }
                }
        );

        takePictureButton.setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }
            else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });
    }
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch(takePictureIntent);
    }
}