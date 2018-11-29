package com.dml.base.view.ui.transaction

import com.dml.base.base.BaseContract

class TransactionContract {
    interface View : BaseContract.View<Presenter> {
        fun turnOnDateButton()
        fun turnOnPendingButton()
        fun turnOnTokenButton()
        fun turnOnFiatValueButton()
        fun turnOffDateButton()
        fun turnOffPendingButton()
        fun turnOffTokenButton()
        fun turnOffFiatValueButton()
    }

    interface Presenter : BaseContract.Presenter {
        fun onDateButtonClicked()
        fun onPendingButtonClicked()
        fun onTokenButtonClicked()
        fun onFiatValueButtonClicked()
    }
}
