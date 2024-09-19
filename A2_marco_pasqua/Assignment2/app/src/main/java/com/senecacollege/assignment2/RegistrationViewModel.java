package com.senecacollege.assignment2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrationViewModel extends ViewModel {
    private String participantName;
    private String participantAge;

    private String schoolName;
    private String teamName;
    private String teamMemberNames;
    private String participantHopeToLearn;
    private String gender;
    private String termsAndConditionsSelection;
    private String photoSharingSelection;
    private MutableLiveData<Integer> registrationProgressLiveData = new MutableLiveData<>(0);

    public LiveData<Integer> getRegistrationProgressLiveData() {
        return registrationProgressLiveData;
    }

    public void setRegistrationProgress(int progress) {
        registrationProgressLiveData.setValue(progress);
    }

    public String getTermsAndConditionsSelection() {
        return termsAndConditionsSelection;
    }

    public void setTermsAndConditionsSelection(String termsAndConditionsSelection) {
        this.termsAndConditionsSelection = termsAndConditionsSelection;
    }

    public String getPhotoSharingSelection() {
        return photoSharingSelection;
    }

    public void setPhotoSharingSelection(String photoSharingSelection) {
        this.photoSharingSelection = photoSharingSelection;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantAge() {
        return participantAge;
    }

    public void setParticipantAge(String participantAge) {
        this.participantAge = participantAge;
    }

    public String getParticipantHopeToLearn() {
        return participantHopeToLearn;
    }

    public void setParticipantHopeToLearn(String participantHopeToLearn) {
        this.participantHopeToLearn = participantHopeToLearn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMemberNames() {
        return teamMemberNames;
    }

    public void setTeamMemberNames(String teamMemberNames) {
        this.teamMemberNames = teamMemberNames;
    }
}
