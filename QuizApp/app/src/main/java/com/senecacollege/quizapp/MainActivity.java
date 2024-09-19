package com.senecacollege.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question questions = new Question();


        questionTextView = findViewById(R.id.questionTextView);
        button1 = findViewById(R.id.answer1);
        button2 = findViewById(R.id.answer2);
        button3 = findViewById(R.id.answer3);
        button4 = findViewById(R.id.answer4);

        if (questions.getCurrentQuestion() == 1) {
            questionTextView.setText(questions.getQuestion1());
            button1.setText("10");
            button2.setText("3");
            button3.setText("7");
            button4.setText("12");
        }
        else if (questions.getCurrentQuestion() == 2){

            questionTextView.setText("questions.getQuestion2()");

        }
        else if (questions.getCurrentQuestion() == 13) {
            questionTextView.setText(questions.getQuestion3());
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(questions.getCurrentQuestion() == 1){
                    Toast.makeText(getApplicationContext(), "You got the right answer!", Toast.LENGTH_SHORT).show();
                    questions.setNumOfCorrectAnswers(1);
                    questions.setCurrentQuestion(2);
                }
            }
        });

    }
}