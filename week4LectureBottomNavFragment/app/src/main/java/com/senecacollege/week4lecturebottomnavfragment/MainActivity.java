package com.senecacollege.week4lecturebottomnavfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, homeFragment, "homeFragment")
                .add(R.id.container, exploreFragment, "exploreFragment")
                .hide(exploreFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home){
                    getSupportFragmentManager().beginTransaction().hide(exploreFragment).show(homeFragment).commit();
                    return true;
                }
                else if(id == R.id.explore){
                    getSupportFragmentManager().beginTransaction().hide(homeFragment).show(exploreFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }


}
