package com.senecacollege.dbapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertButton = findViewById(R.id.insertButton);
        Button updateButton = findViewById(R.id.updateButton);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);

        ArrayList<User> users = new ArrayList<>(Arrays.asList(
                new User("John", "Doe"),
                new User("John", "Smith")
        ));

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userdb").build();
        userDao = db.userDao();


        adapter = new RecyclerAdapter(users);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        insertButton.setOnClickListener(this::insertData);

        updateButton.setOnClickListener(view -> {

        });

    }

    public void insertData(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(firstNameEditText.getText().toString(),
                                     lastNameEditText.getText().toString());
                userDao.insert(user);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fetchData(view);
                    }
                });
            }

        }).start();
    }

    public void fetchData(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = userDao.getAll();
                StringBuilder userData = new StringBuilder();
                users.

//                adapter.setData(users);
            }
        }).start();
    }
    //    public void insertData(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = new User("John", "Doe");
//                userDao.insert(user);
//            }
//        }).start();
//    }
//
//    public void fetchData(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<User> users = userDao.getAll();
//
//                for (User user : users) {
//                    Log.d("MainActivity", "User: " + user.uid + ", " + user.firstName + " " + user.lastName);
//                }
//            }
//        }).start();
//    }
//
//    public void updateData(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User userToUpdate = userDao.findByName("John", "Doe");
//                if (userToUpdate != null) {
//                    userToUpdate.lastName = "Smith";
//                    userDao.update(userToUpdate);
//                }
//            }
//        }).start();
//    }

    public void deleteData(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User userToDelete = userDao.findByName("John", "Doe");
                if (userToDelete != null) {
                    userDao.delete(userToDelete);
                }
            }
        }).start();
    }
}