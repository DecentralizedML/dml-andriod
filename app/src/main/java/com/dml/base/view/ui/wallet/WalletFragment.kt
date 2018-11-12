package com.dml.base.view.ui.wallet

import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseFragment

class WalletFragment : BaseFragment(), WalletContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = WalletFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: WalletContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.fragment_wallet
    }

    override fun connectViews() {
    }

    override fun setPresenter(presenter: WalletContract.Presenter) {
        this.presenter = presenter
    }
}