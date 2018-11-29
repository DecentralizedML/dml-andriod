package com.dml.base.view.ui.cashout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.view.ui.QRCodeScannerActivity
import kotlinx.android.synthetic.main.fragment_cashout.*

class CashOutFragment : BaseFragment(), CashOutContract.View {

    companion object {
        const val REQUEST_QR_CODE = 1001

        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = CashOutFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: CashOutContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = CashOutPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_cashout
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.fragment_cash_out_toolbar_title)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
        }

        qrCodeButton?.setOnClickListener {
            mParentActivity.startActivityForResult(Intent(mParentActivity, QRCodeScannerActivity::class.java), REQUEST_QR_CODE)
        }
    }

    override fun setPresenter(presenter: CashOutContract.Presenter) {
        this.presenter = presenter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_QR_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                toAddressEditText.setText(data?.getStringExtra("result"))
            }
        }
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}