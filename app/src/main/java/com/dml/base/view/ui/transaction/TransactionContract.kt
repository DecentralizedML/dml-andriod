package com.dml.base.view.ui.transaction

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class TransactionContract {
    interface View : BaseView<Presenter> {
        fun turnOnDateButton()
        fun turnOnPendingButton()
        fun turnOnTokenButton()
        fun turnOnFiatValueButton()
        fun turnOffDateButton()
        fun turnOffPendingButton()
        fun turnOffTokenButton()
        fun turnOffFiatValueButton()
    }

    interface Presenter : BasePresenter {
        fun onDateButtonClicked()
        fun onPendingButtonClicked()
        fun onTokenButtonClicked()
        fun onFiatValueButtonClicked()
    }
}
