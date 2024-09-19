package com.senecacollege.assignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FeedbackFragment extends Fragment {

    private SeekBar ratingSeekbar;
    private TextView ratingTextView;
    private Switch emailCopySwitch;
    private Button submitButton;
    private EditText emailEditText;
    private EditText userFeedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

         ratingSeekbar = rootView.findViewById(R.id.ratingSeekbar);
         ratingTextView = rootView.findViewById(R.id.ratingTextView);
         emailCopySwitch = rootView.findViewById(R.id.emailCopySwitch);
         submitButton = rootView.findViewById(R.id.submitButton);
         emailEditText = rootView.findViewById(R.id.emailPrompt);
         userFeedback = rootView.findViewById(R.id.userFeedback);

         ratingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int rating, boolean b) {
                 int displayedRating = rating + 1;

                 ratingTextView.setText("Rate Seneca Hackathons (" + displayedRating + "/5):");
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });

         emailCopySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                 String notificationText = isChecked ? "Yes" : "No";
                if (isChecked){
                    emailEditText.setVisibility(View.VISIBLE);
                }
                else{
                    emailEditText.setVisibility(View.INVISIBLE);
                }
             }
         });

        submitButton.setOnClickListener(view -> {
            boolean isEmailCopyEnabled = emailCopySwitch.isChecked();
            boolean mandatoryFieldsFilled = !TextUtils.isEmpty(userFeedback.getText());


            if(mandatoryFieldsFilled){

                if (isEmailCopyEnabled){
                    String email = emailEditText.getText().toString().trim();
                    if(email.isEmpty()){
                        Toast.makeText(getContext(), "Please provide an email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                FragmentManager fragmentManager = getParentFragmentManager();

                Toast.makeText(getContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();

                // Replace the current fragment with the first step fragment
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, feedbackFragment);
                fragmentTransaction.commit();
            }
            else if (!mandatoryFieldsFilled){
                Toast.makeText(getContext(), "Please fill out the mandatory fields", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}