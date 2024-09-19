package com.senecacollege.assignment4;

import static com.senecacollege.assignment4.JsonParser.parseJson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserDao userDao;
    private static List<User> users;
    private static RecyclerView recyclerView;
    public static RecyclerAdapter adapter;
    private static final String PREF_KEY_FIRST_TIME = "first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new RecyclerAdapter(new ArrayList<>());

        adapter.setOnItemClickListener(user -> {
            Intent intent = new Intent(MainActivity.this, CustomerDetailsActivity.class);
            intent.putExtra("userId", user.getUid());
            startActivity(intent);
            fetchData();
        });
        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userdb").build();
        userDao = db.userDao();

//        resetFirstTimeFlag();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstTimeAppLaunch = preferences.getBoolean(PREF_KEY_FIRST_TIME, true);

        if (isFirstTimeAppLaunch) {
            insertJsonData();

            setProcessStatus();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREF_KEY_FIRST_TIME, false);
            editor.apply();
        }
//        else {
//            fetchData();
//        }
            fetchData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                User deletedUser = adapter.getUserAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Are you sure you want to delete this user?");
                builder.setTitle("Warning!");
                // Not allowing the user to click outside of the alert box
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener)
                        (dialog, which) -> {
                            deleteUser(position, deletedUser);
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener)
                                (dialog, which) -> {
                                    adapter.notifyItemChanged(position); //Ned to notify the adapter to bring the item back, doesn't return the item without this line
                });

                builder.show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void fetchData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = userDao.getAllUsers();
                runOnUiThread(() -> {
                    adapter.setData(userList);
                });
            }
        }).start();
    }

    public void insertJsonData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Inside the Thread");
                    InputStream inputStream = getResources().openRawResource(R.raw.users);
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    String json = new String(buffer, StandardCharsets.UTF_8);
                    List<User> userList = parseJson(json);
                    userDao.insertAll(userList);

                    fetchData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void deleteUser(int position, User deletedUser){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(deletedUser);
                runOnUiThread(() -> {
                    adapter.removeUserAtPosition(position);
                    showDeletedUserSnackBar(deletedUser); // Allow for the chance to undo the action
                });
            }
        }).start();
    }

    private void showDeletedUserSnackBar(User deletedUser){
        Snackbar.make(recyclerView,
                "Deleted User: " + deletedUser.getName(),
                Snackbar.LENGTH_LONG).setAction("Undo", view -> {
                    undoUserDeletion(deletedUser);
        }).show();
    }

    private void undoUserDeletion(User deletedUser){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.insertAll(Collections.singletonList(deletedUser));
                List<User> updatedUsers = userDao.getAllUsers();

                runOnUiThread(() -> {
                    adapter.setData(updatedUsers);
                });
            }
        }).start();
    }

    private void setProcessStatus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.setDefaultProcessStatus();
            }
        }).start();
    }

    // DEBUGGING: Resets the shared preference, comment out of final code, or leave in here
    private void resetFirstTimeFlag(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_KEY_FIRST_TIME, true);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }
}

