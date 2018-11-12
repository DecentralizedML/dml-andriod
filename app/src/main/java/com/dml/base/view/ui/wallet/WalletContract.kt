package com.dml.base.view.ui.wallet

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class WalletContract {
    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}