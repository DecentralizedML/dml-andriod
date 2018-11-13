package com.dml.base.view.ui.transaction

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class TransactionContract {
    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}
