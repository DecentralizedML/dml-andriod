package com.dml.base.view.ui.signup.information

import com.dml.base.base.BaseContract
import com.dml.base.network.model.UserSignUpRequest

class SignUpInformationContract {
    interface View : BaseContract.View<Presenter> {
        fun showDatePicker()
        fun showCountryDialog()
        fun tintMaleButton()
        fun tintFemaleButton()
        fun tintOtherGenderButton()
        fun showIncompleteInformationDialog()

        fun saveJWT(jwt: String)
        fun redirectToConnectPage()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSkipButtonClicked()
        fun onDateOfBirthButtonClicked()
        fun onCountryButtonClicked()
        fun onMaleButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onFemaleButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onOtherGenderButtonClicked(gender: SignUpInformationFragment.Gender)
        fun onNextButtonClicked(firstName: String
                                , lastName: String
                                , country: String
                                , dateOfBirth: String
                                , gender: String
                                , educationLevel: String)

        fun updateUserRequest(userUpdateRequest: UserSignUpRequest)
    }
}