package com.senecacollege.toolbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ // To set the toolbar in the code, we must use this function and have the 2 lines of code on line 16 and 17
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_login){
            new AlertDialog.Builder(this)
                    .setTitle("Search")
                    .setMessage("Do you want to login?")
                    .setIcon(R.drawable.baseline_login_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int btn) {
                            Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null).show();
            return true;
        } else if (id == R.id.action_settings) {

            /*new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                }
            }, 2022, 0, 1).show();*/

            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {

                }
            },12, 0, true).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}