package com.dml.base.view.ui.fragment.signup.complete

import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.view.activity.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_complete.*

class SignUpCompleteFragment : BaseFragment(), SignUpCompleteContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpCompleteFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter : SignUpCompleteContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpCompletePresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_complete
    }

    override fun connectViews() {
        checkOutButton?.apply {
            setText(R.string.activity_signup_complete_button_check_out)
            showRightIcon(true)
            setOnClickListener { checkOut() }
        }
    }

    override fun setPresenter(presenter: SignUpCompleteContract.Presenter) {
        this.presenter = presenter
    }

    private fun checkOut() {

    }

    private fun redirectToSignUpComplete() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
    }
}