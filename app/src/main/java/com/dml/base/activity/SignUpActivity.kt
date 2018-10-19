package com.dml.base.activity

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.R
import com.dml.base.adapter.SignUpViewPagerAdapter
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : BaseActivity() {

    sealed class SignUpState {
        object First : SignUpState()
        object Second : SignUpState()
        object Third : SignUpState()
        object Complete : SignUpState()
    }

    override fun connectViews() {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_signup
    }

    var viewPagerAdapter: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPagerAdapter = SignUpViewPagerAdapter(supportFragmentManager)
        pager?.adapter = viewPagerAdapter
        pager?.offscreenPageLimit = 0
        setState(SignUpState.First)
    }

    public fun setState(state: SignUpState) {
        when (state) {
            SignUpState.First -> {
                pager?.setCurrentItem(0, true)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFirstIV?.setImageResource(R.drawable.ic_welcome_ongoing)
                stageSecondIV?.setImageResource(0)
                stageThirdIV?.setImageResource(0)
            }
            SignUpState.Second -> {
                pager?.setCurrentItem(1, true)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFirstIV?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondIV?.setImageResource(R.drawable.ic_welcome_ongoing)
                stageThirdIV?.setImageResource(0)
            }
            SignUpState.Third -> {
                pager?.setCurrentItem(2, true)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageFirstIV?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondIV?.setImageResource(R.drawable.ic_welcome_done)
                stageThirdIV?.setImageResource(R.drawable.ic_welcome_ongoing)

            }
            SignUpState.Complete -> {
                pager?.setCurrentItem(3, true)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageFirstIV?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondIV?.setImageResource(R.drawable.ic_welcome_done)
                stageThirdIV?.setImageResource(R.drawable.ic_welcome_done)
            }
        }
    }
}