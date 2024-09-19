package com.senecacollege.assignment4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.w3c.dom.Text;

public class CustomerDetailsActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao userDao;

    private TextView userIdTextView;
    private TextView personNameTextView;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView streetTextView;
    private TextView suiteTextView;
    private TextView cityTextView;
    private TextView zipCodeTextView;
    private TextView latTextView;
    private TextView lngTextView;

    private EditText phoneNumberEditText;

    private TextView websiteTextView;
    private TextView companyNameTextView;
    private TextView companyCatchPhraseTextView;
    private TextView companyBsTextView;
    private TextView processStatusTextView;

    private RadioGroup processStatusRadioGroup;

    private Button goBackButton;
    private Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userdb").build();
        userDao = db.userDao();

        // setting all UI elements
        userIdTextView = findViewById(R.id.userId);
        personNameTextView = findViewById(R.id.personName);
        usernameTextView = findViewById(R.id.username);
        emailTextView = findViewById(R.id.email);
        streetTextView = findViewById(R.id.streetAddress);
        suiteTextView = findViewById(R.id.suiteAddress);
        cityTextView = findViewById(R.id.city);
        zipCodeTextView = findViewById(R.id.zipcode);
        latTextView = findViewById(R.id.lat);
        lngTextView = findViewById(R.id.lng);
        processStatusTextView = findViewById(R.id.processStatus);

        phoneNumberEditText = findViewById(R.id.phoneNumber);

        websiteTextView = findViewById(R.id.website);
        companyNameTextView = findViewById(R.id.companyName);
        companyCatchPhraseTextView = findViewById(R.id.companyCatchPhrase);
        companyBsTextView = findViewById(R.id.companyBs);

        processStatusRadioGroup = findViewById(R.id.processStatusRadioGroup);

        goBackButton = findViewById(R.id.goBackButton);
        submitButton = findViewById(R.id.submitButton);


        // Get user ID from intent
        int userId = getIntent().getIntExtra("userId", -1);

        if (userId != -1){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    User user = userDao.getUserById(userId);
                    runOnUiThread(() -> {
                        if (user != null){
                            userIdTextView.setText("User ID: " + String.valueOf(user.getUid()));
                            personNameTextView.setText("Name: " + user.getName());
                            usernameTextView.setText("Username: " + user.getUsername());
                            emailTextView.setText("Email: " + user.getEmail());
                            streetTextView.setText("Street: " + user.getAddress().getStreet());
                            suiteTextView.setText("Suite: " + user.getAddress().getSuite());
                            cityTextView.setText("City: " + user.getAddress().getCity());
                            zipCodeTextView.setText("Zip Code: " + user.getAddress().getZipcode());
                            latTextView.setText("Latitude: " + user.getAddress().getGeo().getLat());
                            lngTextView.setText("Longitude: " + user.getAddress().getGeo().getLng());

                            phoneNumberEditText.setText(user.getPhone(), TextView.BufferType.EDITABLE);

                            websiteTextView.setText("Website: " + user.getWebsite());
                            companyNameTextView.setText("Company Name: " + user.getCompany().getName());
                            companyCatchPhraseTextView.setText("Company Catch Phrase: " + user.getCompany().getCatchPhrase());
                            companyBsTextView.setText("Company bs: " + user.getCompany().getBs());
                            processStatusTextView.setText("Current Process Status: " + user.getProcessStatus());
                        }
                    });
                }
            }).start();
        }

        goBackButton.setOnClickListener(view -> {
            finish();
        });

        submitButton.setOnClickListener(view -> {
            new Thread(() -> {
                int selectedRadioButtonId = processStatusRadioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedProcessStatus = selectedRadioButton.getText().toString();

                    User user = userDao.getUserById(userId);

                    if (user != null) {
                        user.setProcessStatus(selectedProcessStatus);

                        String newPhoneNumber = phoneNumberEditText.getText().toString();
                        user.setPhone(newPhoneNumber);

                        userDao.updateUser(user);

                        runOnUiThread(() -> {
                            processStatusTextView.setText("Current Process Status: " + user.getProcessStatus());
                            phoneNumberEditText.setText(user.getPhone(), TextView.BufferType.EDITABLE);
                        });
                    }
                }
            }).start();
        });
    }
}
