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
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationFormFragment extends Fragment {
    private RegistrationViewModel viewModel;
    private EditText nameEditText;
    private EditText ageEditText;
    private RadioGroup genderRadioGroup;
    private Button nextButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registration_step1, container, false);
        viewModel.setRegistrationProgress(0); //Even though this is defined to be 0 in the class, if the user switches fragments, it saves the value in the progress bar
        // Initializing elements
        nameEditText = rootView.findViewById(R.id.participantName);
        ageEditText = rootView.findViewById(R.id.participantAge);
        genderRadioGroup = rootView.findViewById(R.id.genderRadioGroup);
        nextButton = rootView.findViewById(R.id.nextButton);

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
                boolean allFieldsFilled = !TextUtils.isEmpty(nameEditText.getText())
                        && !TextUtils.isEmpty(ageEditText.getText())
                        && genderRadioGroup.getCheckedRadioButtonId() != -1;

                if (allFieldsFilled) {
                    int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                    if(selectedRadioButtonId == R.id.radioButtonMale){
                        viewModel.setGender("Male");
                    }
                    else if(selectedRadioButtonId == R.id.radioButtonFemale){
                        viewModel.setGender("Female");
                    }
                    else if(selectedRadioButtonId == R.id.radioButtonOther){
                        viewModel.setGender("Other");
                    }
                    viewModel.setParticipantName(nameEditText.getText().toString().trim());
                    viewModel.setParticipantAge(ageEditText.getText().toString().trim());

                    //Progress bar logic
                    int totalSteps = 4; // Number of steps in registration progress
                    int completedSteps = 1;
                    int progress = (completedSteps * 100) / totalSteps;

                    viewModel.setRegistrationProgress(progress);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new RegistrationFormStep2Fragment());
                    fragmentTransaction.addToBackStack(null); // Optional: Add to back stack
                    fragmentTransaction.commit();
                } else {
                    // Show an error message to the user if fields are not filled
                    Toast.makeText(getContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}