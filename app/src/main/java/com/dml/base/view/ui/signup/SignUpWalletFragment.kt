package com.dml.base.view.ui.signup

import android.net.wifi.hotspot2.pps.Credential
import android.os.Bundle
import android.view.View
import com.dml.base.Configure
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.utils.web3j.WalletUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_signup_wallet.*
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import java.io.File


class SignUpWalletFragment : BaseFragment() {

    private var web3j = Web3jFactory.build(HttpService(Configure.ETHEREUM_ENDPOINT))

    private var password = "ForTesting"

    private var mnemonic = ""

    private var credentials: Credential? = null

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpWalletFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mCompositeDisposable.add(Observable.fromCallable { createWallet() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mnemonicTextView?.text = mnemonic
                    progressBar?.visibility = View.GONE
                })
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_wallet
    }

    override fun connectViews() {
        copyButton.setOnClickListener {
            Utility.setClipboard(context, "", mnemonic)
        }
    }

    private fun createWallet() {
        val path = mParentActivity.filesDir
        val bip39Wallet = WalletUtils.generateBip39Wallet(password, File(path.toString()))
        val fileName = bip39Wallet.filename
        mnemonic = bip39Wallet.mnemonic
        credentials?.let { WalletUtils.loadBip39Credentials(password, mnemonic) }
    }
}