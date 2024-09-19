package com.senecacollege.microphoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button recordButton;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private boolean isListening = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        recordButton = findViewById(R.id.recordButton);

        // Check permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        // Initalize Speech Recognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); // Set Up: Part 1
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // Recognizes Speech based on where it's being run. EX. Being run in Canada, picks up English

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {
                tvResult.setText("Listening...");
            }

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                tvResult.setText("Something went wrong. Please try again");
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(matches != null){
                    tvResult.setText(matches.get(0));
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        recordButton.setOnClickListener(view -> {
            if(isListening){
                speechRecognizer.stopListening();
                recordButton.setText("Start Recording");
            } else{
                speechRecognizer.startListening(speechRecognizerIntent);
                recordButton.setText("Stop Recording");
            }
            isListening = !isListening;
        });
    }

    @Override
    protected void onDestroy() { // Making sure to destroy speechRecognizer app is closed
        super.onDestroy();

        if(speechRecognizer != null){
            speechRecognizer.destroy();
        }
    }
}