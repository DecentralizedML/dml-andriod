package com.dml.base.view.ui.signup.information

import com.dml.base.base.BaseContract
import com.dml.base.network.model.UserUpdateRequest

class SignUpInformationContract {
    interface View : BaseContract.View<Presenter> {
        fun showDatePicker()
        fun showCountryDialog()
        fun tintMaleButton()
        fun tintFemaleButton()
        fun tintOtherGenderButton()
        fun showIncompleteInformationDialog()

        fun redirectToConnectPage()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSkipButtonClicked()
        fun onDateOfBirthButtonClicked()
        fun onCountryButtonClicked()
        fun onMaleButtonClicked(gender: String)
        fun onFemaleButtonClicked(gender: String)
        fun onOtherGenderButtonClicked(gender: String)
        fun onNextButtonClicked(firstName: String
                                , lastName: String
                                , country: String
                                , dateOfBirth: String
                                , gender: String
                                , educationLevel: String)

        fun updateUserRequest(userUpdateRequest: UserUpdateRequest)
    }
}