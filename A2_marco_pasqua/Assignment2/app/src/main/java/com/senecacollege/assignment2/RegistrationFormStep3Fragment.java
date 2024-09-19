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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationFormStep3Fragment extends Fragment {

    private RegistrationViewModel viewModel;
    private RadioGroup termsAndConditionsRadio;
    private RadioGroup photoSharingRadio;
    private Button nextButton;
    private Button goBackButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registration_form_step3, container, false);

        // Initializing elements
        termsAndConditionsRadio = rootView.findViewById(R.id.termsAndConditionsSelection);
        photoSharingRadio = rootView.findViewById(R.id.photoTakenSelection);
        nextButton = rootView.findViewById(R.id.nextButton);
        goBackButton = rootView.findViewById(R.id.goBackButton);

        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        // Observe the progress value from the shared ViewModel
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
                boolean allFieldsFilled = termsAndConditionsRadio.getCheckedRadioButtonId() != -1
                        && photoSharingRadio.getCheckedRadioButtonId() != -1;
                if(allFieldsFilled){
                    int selectedRadioButtonId = termsAndConditionsRadio.getCheckedRadioButtonId();

                    if(selectedRadioButtonId == R.id.radioButtonYes){
                        viewModel.setTermsAndConditionsSelection("Yes");
                    }
                    else if(selectedRadioButtonId == R.id.radioButtonNo){
                        viewModel.setTermsAndConditionsSelection("No");
                    }

                    selectedRadioButtonId = photoSharingRadio.getCheckedRadioButtonId();

                    if(selectedRadioButtonId == R.id.radioButtonYesPhoto){
                        viewModel.setPhotoSharingSelection("Yes");
                    }
                    else if(selectedRadioButtonId == R.id.radioButtonNoPhoto){
                        viewModel.setPhotoSharingSelection("No");
                    }

                    //Progress bar logic
                    int totalSteps = 4; // Number of steps in registration progress
                    int completedSteps = 3;
                    int progress = (completedSteps * 100) / totalSteps;

                    viewModel.setRegistrationProgress(progress);

                    FragmentManager fragmentManger = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManger.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new RegistrationFormStep4Fragment());
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
                //Returns to the second step
                getParentFragmentManager().popBackStack();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }
}