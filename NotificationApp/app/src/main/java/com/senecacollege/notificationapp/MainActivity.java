package com.senecacollege.notificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText deadlineEditText;
    private Calendar deadlineCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        deadlineEditText = findViewById(R.id.deadlineEditText);
        deadlineCalendar = Calendar.getInstance();

        deadlineEditText.setOnClickListener(view -> {
            showDateTimeDialog();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(!NotificationManagerCompat.from(this).areNotificationsEnabled()){
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 12);
            }
        }
    }

    private void showDateTimeDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        deadlineCalendar.set(Calendar.YEAR, year);
                        deadlineCalendar.set(Calendar.MONTH, month);
                        deadlineCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        showTimePicker();
                    }
                },
                deadlineCalendar.get(Calendar.YEAR),
                deadlineCalendar.get(Calendar.MONTH),
                deadlineCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        deadlineCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        deadlineCalendar.set(Calendar.MINUTE, minute);
                        deadlineCalendar.set(Calendar.SECOND, 0);
                        deadlineCalendar.set(Calendar.MILLISECOND, 0);
                        updateLabel();
                    }
                },
                deadlineCalendar.get(Calendar.HOUR_OF_DAY),
                deadlineCalendar.get(Calendar.MINUTE),
                false);

        timePickerDialog.show();
    }
    private void updateLabel() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("EEE, MMM d, yyyy, HH:mm", Locale.getDefault());
        deadlineEditText.setText(dateTimeFormat.format(deadlineCalendar.getTime()));
    }

    private void scheduleNotification(String title, long deadlineMillis) {
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("TODO_TITLE", title);

        Log.d("MainActivity", "Scheduling notification at: " + new Date(deadlineMillis).toString());

        int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_IMMUTABLE : 0;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intent, flags | PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, deadlineMillis, pendingIntent);
        }
    }

    public void addTodo(View view) {
        String title = titleEditText.getText().toString();
        long deadlineMillis = deadlineCalendar.getTimeInMillis();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager == null) {
            Toast.makeText(this, "Alarm service not available", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            Log.d("MainActivity", "Requesting permission for exact alarms");
            Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
            startActivity(intent);
        } else {
            Toast.makeText(this, "To-Do added: " + title, Toast.LENGTH_SHORT).show();
            scheduleNotification(title, deadlineMillis);
        }
    }

}