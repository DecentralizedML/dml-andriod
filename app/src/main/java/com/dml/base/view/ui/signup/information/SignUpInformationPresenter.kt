package com.dml.base.view.ui.signup.information

import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.ErrorResponse
import com.dml.base.network.api.service.APIService
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import com.dml.base.view.ui.signup.information.SignUpInformationFragment.Gender
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignUpInformationPresenter(var view: SignUpInformationContract.View
                                 , var service: APIService
                                 , var compositeDisposable: CompositeDisposable) : SignUpInformationContract.Presenter {

    private var selectedGender: SignUpInformationFragment.Gender? = null

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onSkipButtonClicked() {
        view.redirectToConnectPage()
    }

    override fun onDateOfBirthButtonClicked() {
        view.showDatePicker()
    }

    override fun onCountryButtonClicked() {
        view.showCountryDialog()
    }

    override fun onMaleButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Gender.Male
            view.tintMaleButton()
        }
    }

    override fun onFemaleButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Gender.Female
            view.tintFemaleButton()
        }
    }

    override fun onOtherGenderButtonClicked(gender: Gender) {
        if (selectedGender == null || selectedGender != null && selectedGender != gender) {
            selectedGender = Gender.Other
            view.tintOtherGenderButton()
        }
    }

    override fun onNextButtonClicked(firstName: String
                                     , lastName: String
                                     , country: String
                                     , dateOfBirth: String
                                     , gender: String
                                     , educationLevel: String) {

        val userUpdateRequest = UserSignUpRequest()
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

    override fun updateUserRequest(userUpdateRequest: UserSignUpRequest) {
        service.updateUserRequest(userUpdateRequest)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserSignUpResponse>() {
                    override fun onErrorResponse(errorResponse: ErrorResponse) {
                        view.showErrorResponse(errorResponse)
                    }

                    override fun onErrorUnknown() {
                    }

                    override fun onNext(modelUser: UserSignUpResponse) {
                        view.saveJWT(modelUser.meta.jwt)
                        view.redirectToConnectPage()
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