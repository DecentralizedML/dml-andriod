package com.dml.base.view.ui.signup.signup

import com.dml.base.Utility
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.ErrorResponse
import com.dml.base.network.api.service.APIService
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignUpPresenter(var view: SignUpContract.View
                      , var service: APIService
                      , var compositeDisposable: CompositeDisposable) : SignUpContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {}

    override fun onSignUpButtonClicked(email: String, password: String, isChecked: Boolean) {
        var isValid = true

        if (email.isEmpty() or email.isBlank()) {
            view.showEmailError()
            isValid = false
        } else if (!Utility.isValidEmail(email)) {
            view.showEmailError()
            isValid = false
        } else {
            view.showEmailNoError()
        }

        if (password.isEmpty() or password.isBlank()) {
            view.showPasswordError()
            isValid = false
        } else if (password.length < 8) {
            view.showPasswordError()
            isValid = false
        } else {
            view.showPasswordNoError()
        }

        if (!isChecked) {
            isValid = false
        }

        if (isValid) {
            val signUpRequestModel = UserSignUpRequest()
            signUpRequestModel.apply {
                user.apply {
                    this.email = email
                    this.password = password
                    this.passwordConfirmation = password
                }
            }

            postSignUpRequest(signUpRequestModel)
        }
    }

    override fun postSignUpRequest(signUpRequestModel: UserSignUpRequest) {
        service.postUserSignUpRequest(signUpRequestModel)
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
                        view.redirectToInformationPage()
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