package com.senecacollege.week4lecturebottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home){
                    return true;
                }
                else if (id == R.id.explore){
                    startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        System.out.println("Destroyed");
    }
}
