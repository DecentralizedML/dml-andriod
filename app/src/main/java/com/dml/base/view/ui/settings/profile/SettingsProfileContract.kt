package com.dml.base.view.ui.settings.profile

import com.dml.base.base.BaseContract
import com.dml.base.network.model.UserUpdateRequest

class SettingsProfileContract {
    interface View : BaseContract.View<Presenter> {
        fun showDatePicker()
        fun showCountryDialog()
        fun tintMaleButton()
        fun tintFemaleButton()
        fun tintOtherGenderButton()
        fun showIncompleteInformationDialog()
        fun showUpdateSuccessDialog()
    }

    interface Presenter : BaseContract.Presenter {
        fun onDateOfBirthButtonClicked()
        fun onCountryButtonClicked()
        fun onMaleButtonClicked(gender: String)
        fun onFemaleButtonClicked(gender: String)
        fun onOtherGenderButtonClicked(gender: String)
        fun onUpdateButtonClicked(firstName: String
                                  , lastName: String
                                  , country: String
                                  , dateOfBirth: String
                                  , gender: String
                                  , educationLevel: String)

        fun getUserRequest(userUpdateRequest: UserUpdateRequest)
        fun updateUserRequest(userUpdateRequest: UserUpdateRequest)
    }
}
