package com.senecacollege.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    WelcomeFragment welcomeFragment = new WelcomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    RegistrationFormFragment registrationFormFragment = new RegistrationFormFragment();
    FeedbackFragment feedbackFragment = new FeedbackFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, welcomeFragment, "welcomeFragment")
                .add(R.id.container, exploreFragment, "helloFragment")
                .add(R.id.container, registrationFormFragment, "registrationFormFragment")
                .add(R.id.container, feedbackFragment, "feedbackFragment")
                .hide(feedbackFragment).hide(exploreFragment).hide(registrationFormFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.welcome) {
                    WelcomeFragment welcomeFragment = new WelcomeFragment(); //Must make a new instance of the fragment otherwise it won't load on first click
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, welcomeFragment)
                            .commit();
                    return true;
                } else if (id == R.id.hello) {
                    ExploreFragment exploreFragment = new ExploreFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, exploreFragment)
                            .commit();
                    return true;
                } else if (id == R.id.registration) {
                    RegistrationFormFragment registrationFormFragment = new RegistrationFormFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, registrationFormFragment)
                            .commit();
                    return true;
                } else if (id == R.id.feedback) {
                    FeedbackFragment feedbackFragment = new FeedbackFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, feedbackFragment)
                            .commit();
                    return true;
                }

                return false;
            }
        });

    }
}