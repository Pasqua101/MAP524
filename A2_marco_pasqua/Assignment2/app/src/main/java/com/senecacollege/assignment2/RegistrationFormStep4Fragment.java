package com.senecacollege.assignment2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class RegistrationFormStep4Fragment extends Fragment {
    private RegistrationViewModel viewModel;

    private Button submitButton;
    private Button goBackButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_form_step4, container, false);

        submitButton = rootView.findViewById(R.id.submitButton);
        goBackButton = rootView.findViewById(R.id.goBackButton);


        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        // Get the progress value from the ViewModel
        viewModel.getRegistrationProgressLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer progress) {
                // Update the progress bar
                progressBar.setProgress(progress);
            }
        });


        TextView participantNameTextView = rootView.findViewById(R.id.participantNameTextView);
        participantNameTextView.setText(viewModel.getParticipantName());

        TextView participantAgeTextView = rootView.findViewById(R.id.participantAgeTextView);
        participantAgeTextView.setText(viewModel.getParticipantAge());

        TextView participantGender = rootView.findViewById(R.id.genderTextView);
        participantGender.setText(viewModel.getParticipantAge());

        TextView schoolNameTextView = rootView.findViewById(R.id.schoolNameTextView);
        schoolNameTextView.setText(viewModel.getSchoolName());


        TextView teamNameTextView = rootView.findViewById(R.id.teamNameTextView);
        teamNameTextView.setText(viewModel.getTeamName());

        TextView teamMemberTextView = rootView.findViewById(R.id.teamMemberTextView);
        teamMemberTextView.setText(viewModel.getTeamMemberNames());

        TextView hopeToLearnTextView = rootView.findViewById(R.id.hopeToLearnTextView);
        hopeToLearnTextView.setText(viewModel.getParticipantHopeToLearn());


        TextView termsAndConditionsTextView = rootView.findViewById(R.id.termsAndConditionsTextView);
        termsAndConditionsTextView.setText(viewModel.getTermsAndConditionsSelection());


        TextView photoSharingTextView = rootView.findViewById(R.id.photoSharingTextView);
        photoSharingTextView.setText(viewModel.getPhotoSharingSelection());



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Progress bar logic
                int totalSteps = 4; // Number of steps in registration progress
                int completedSteps = 4;
                int progress = (completedSteps * 100) / totalSteps;

                viewModel.setRegistrationProgress(progress);

                // Clear the backstack
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                Toast.makeText(getContext(), "Thank you for signing up!", Toast.LENGTH_SHORT).show();

                viewModel.setRegistrationProgress(0);
                // Replace the current fragment with the first step fragment
                RegistrationFormFragment step1Fragment = new RegistrationFormFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, step1Fragment);
                fragmentTransaction.commit();

            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        return rootView;
    }
}