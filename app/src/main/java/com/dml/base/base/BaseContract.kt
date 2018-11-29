package com.dml.base.base

import com.dml.base.network.ErrorResponse

interface BaseContract {

    interface View<T> {

        fun setPresenter(presenter: T)
        fun showProgressBar()
        fun dismissProgressBar()
        fun showErrorResponse(errorResponse: ErrorResponse)
    }

    interface Presenter {

        fun start()
    }
}