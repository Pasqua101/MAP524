package com.senecacollege.assignment2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegistrationFormStep2Fragment extends Fragment {
    private RegistrationViewModel viewModel;

    private Button nextButton;
    private Button goBackButton;

    private EditText schoolName;
    private EditText teamName;
    private EditText teamMemberNames;
    private EditText participantHopeToLearn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_step2, container, false);

        // Initializing elements
        nextButton = rootView.findViewById(R.id.nextButton);
        goBackButton = rootView.findViewById(R.id.goBackButton);
        schoolName = rootView.findViewById(R.id.schoolName);
        teamName = rootView.findViewById(R.id.teamName);
        teamMemberNames = rootView.findViewById(R.id.teamMemberNames);
        participantHopeToLearn = rootView.findViewById(R.id.hopeToLearn);

        schoolName.setText(viewModel.getSchoolName());
        teamName.setText(viewModel.getTeamName());
        teamMemberNames.setText(viewModel.getTeamMemberNames());
        participantHopeToLearn.setText(viewModel.getParticipantHopeToLearn());

        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        // Get the progress value from the ViewModel
        viewModel.getRegistrationProgressLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer progress) {
                // Update the progress bar
                progressBar.setProgress(progress);
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean allFieldsFilled = !TextUtils.isEmpty(schoolName.getText())
                        && !TextUtils.isEmpty(teamName.getText())
                        && !TextUtils.isEmpty(teamMemberNames.getText())
                        && !TextUtils.isEmpty(participantHopeToLearn.getText());

                if (allFieldsFilled){
                    // Save data from step two in the ViewModel
                    viewModel.setSchoolName(schoolName.getText().toString().trim());
                    viewModel.setTeamName(teamName.getText().toString().trim());
                    viewModel.setTeamMemberNames(teamMemberNames.getText().toString().trim());
                    viewModel.setParticipantHopeToLearn(participantHopeToLearn.getText().toString().trim());

                    //Progress bar logic
                    int totalSteps = 4; // Number of steps in registration progress
                    int completedSteps = 2;
                    int progress = (completedSteps * 100) / totalSteps;

                    viewModel.setRegistrationProgress(progress);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new RegistrationFormStep3Fragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else {
                    Toast.makeText(getContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data from step two in the ViewModel
                viewModel.setSchoolName(schoolName.getText().toString().trim());
                viewModel.setTeamName(teamName.getText().toString().trim());
                viewModel.setTeamMemberNames(teamMemberNames.getText().toString().trim());
                viewModel.setParticipantHopeToLearn(participantHopeToLearn.getText().toString().trim());
                // Returns to the first step
                getParentFragmentManager().popBackStack();
            }
        });


        return rootView;
    }
}

