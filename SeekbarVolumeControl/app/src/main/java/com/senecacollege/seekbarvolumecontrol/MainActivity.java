package com.senecacollege.seekbarvolumecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView volumeDisplay;
    private SeekBar volumeSeekBar;
    private ImageView volumeImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeDisplay = findViewById(R.id.volumeDisplay);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeImageView = findViewById(R.id.volumeImageView);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int volumeValue, boolean b) {
                volumeValue += 1;
                volumeDisplay.setText("Volume level is set to  " + volumeValue + "/100");
                if(volumeValue == 0){
                    //TODO: Get an image for muted volume
                }
                //TODO: Make two else ifs for 25, 50 and 75 volume. Where each bar increases by one
                else if(volumeValue > 0){
                    volumeImageView.setImageResource(R.drawable.volumeincrease); //TODO: Move this to make volume
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}