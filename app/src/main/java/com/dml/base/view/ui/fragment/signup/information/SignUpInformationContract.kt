package com.dml.base.view.ui.fragment.signup.information

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class SignUpInformationContract {
    interface View : BaseView<Presenter> {
        fun showDatePicker()
        fun showCountryDialog()
        fun tintMaleButton()
        fun tintFemaleButton()
        fun tintOtherGenderButton()
        fun showIncompleteInformationDialog()

        fun updateUserRequest()
        fun redirectToSignUpConnect()
    }

    interface Presenter : BasePresenter {
        fun onSkipButtonClicked()
        fun onDateOfBirthButtonClicked()
        fun onCountryButtonClicked()
        fun onMaleButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onFemaleButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onOtherGenderButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onNextButtonClicked(name: String?
                                , country: String?
                                , dateOfBirth: String?
                                , educationLevel: String?)
    }
}