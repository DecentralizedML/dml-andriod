package com.dml.base.view.ui.fragment.signup.information

import com.dml.base.view.ui.fragment.signup.information.SignUpInformationFragment.Gender

class SignUpInformationPresenter(var view: SignUpInformationContract.View) : SignUpInformationContract.Presenter {

    private var selectedGender: SignUpInformationFragment.Gender? = null

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onSkipButtonClicked() {
        view.redirectToSignUpConnect()
    }

    override fun onDateOfBirthButtonClicked() {
        view.showDatePicker()
    }

    override fun onCountryButtonClicked() {
        view.showCountryDialog()
    }

    override fun onMaleButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender == gender) {
            selectedGender = Gender.Male
            view.tintMaleButton()
        }
    }

    override fun onFemaleButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender == gender) {
            selectedGender = Gender.Female
            view.tintFemaleButton()
        }
    }

    override fun onOtherGenderButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender == gender) {
            selectedGender = Gender.Other
            view.tintOtherGenderButton()
        }
    }

    override fun onNextButtonClicked(name: String?, country: String?, dateOfBirth: String?, educationLevel: String?) {
        if (name.isNullOrEmpty() or name.isNullOrBlank()
                or country.isNullOrEmpty()
                or dateOfBirth.isNullOrEmpty()
                or selectedGender.toString().isNullOrEmpty()
                or educationLevel.isNullOrEmpty()) {
            view.showIncompleteInformationDialog()
        } else {
            view.updateUserRequest()
        }

    }
}