package com.senecacollege.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton);
        val subButton = findViewById<Button>(R.id.subButton);


        val editNo1 = findViewById<EditText>(R.id.editNo1);
        val editNo2 = findViewById<EditText>(R.id.editNo2);

        addButton.setOnClickListener {
            if (editNo1.text.toString().isNotEmpty() && editNo2.text.toString().isNotEmpty()) {

                val no1 = editNo1.text.toString().toInt();
                val no2 = editNo2.text.toString().toInt();

                val sum = no1 + no2;

                Toast.makeText(this, "The sum is $sum", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            }
        }
        subButton.setOnClickListener {
            if (editNo1.text.toString().isNotEmpty() && editNo2.text.toString().isNotEmpty()) {

                val no1 = editNo1.text.toString().toInt();
                val no2 = editNo2.text.toString().toInt();

                val sub = no1 - no2;

                Toast.makeText(this, "The difference is $sub", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            }
        }
    }
}