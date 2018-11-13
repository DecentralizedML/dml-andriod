package com.dml.base.view.ui.transaction.detail

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class TransactionDetailContract {
    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}