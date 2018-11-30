package com.dml.base.view.ui.settings.profile

import com.dml.base.Configure
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.ErrorResponse
import com.dml.base.network.api.service.APIService
import com.dml.base.network.model.UserUpdateRequest
import com.dml.base.network.model.UserUpdateResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SettingsProfilePresenter(var view: SettingsProfileContract.View
                               , var service: APIService
                               , var compositeDisposable: CompositeDisposable) : SettingsProfileContract.Presenter {

    private var selectedGender: String? = null

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onDateOfBirthButtonClicked() {
        view.showDatePicker()
    }

    override fun onCountryButtonClicked() {
        view.showCountryDialog()
    }

    override fun onMaleButtonClicked(gender: String) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Configure.GENDER_MALE
            view.tintMaleButton()
        }
    }

    override fun onFemaleButtonClicked(gender: String) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Configure.GENDER_FEMALE
            view.tintFemaleButton()
        }
    }

    override fun onOtherGenderButtonClicked(gender: String) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Configure.GENDER_OTHER
            view.tintOtherGenderButton()
        }
    }

    override fun onUpdateButtonClicked(firstName: String
                                       , lastName: String
                                       , country: String
                                       , dateOfBirth: String
                                       , gender: String
                                       , educationLevel: String) {

        val userUpdateRequest = UserUpdateRequest()
        userUpdateRequest.apply {
            user.apply {
                if (!firstName.isEmpty() && !firstName.isBlank())
                    this.firstName = firstName
                if (!lastName.isEmpty() && !lastName.isBlank())
                    this.lastName = lastName
                if (!country.isEmpty() && !country.isBlank())
                    this.country = country
                if (!dateOfBirth.isEmpty() && !dateOfBirth.isBlank())
                    this.dateOfBirth = dateOfBirth
                if (!gender.isEmpty() && !gender.isBlank())
                    this.gender = gender
                if (!educationLevel.isEmpty() && !educationLevel.isBlank())
                    this.educationLevel = educationLevel
            }
        }

        updateUserRequest(userUpdateRequest)
    }

    override fun getUserRequest(userUpdateRequest: UserUpdateRequest) {
    }

    override fun updateUserRequest(userUpdateRequest: UserUpdateRequest) {
        service.updateUserRequest(userUpdateRequest)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserUpdateResponse>() {
                    override fun onErrorResponse(errorResponse: ErrorResponse) {
                        view.showErrorResponse(errorResponse)
                    }

                    override fun onErrorUnknown() {
                    }

                    override fun onNext(response: UserUpdateResponse) {
                        view.showUpdateSuccessDialog()
                    }

                    override fun onComplete() {
                        super.onComplete()
                        view.dismissProgressBar()
                    }

                    override fun onStart() {
                        super.onStart()
                        view.showProgressBar()
                    }
                })
                ?.let { compositeDisposable.add(it) }
    }
}