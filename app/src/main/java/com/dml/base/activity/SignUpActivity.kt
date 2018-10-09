package com.dml.base.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import com.dml.base.R
import com.dml.base.adapter.SignUpViewPageAdapter
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

    var adapterViewPager: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapterViewPager = SignUpViewPageAdapter(supportFragmentManager)
        pager?.adapter = adapterViewPager
    }

    public fun setState(state: SignUpState) {
        when (state) {
            SignUpState.First -> {
                pager?.setCurrentItem(0, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            SignUpState.Second -> {
                pager?.setCurrentItem(1, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            SignUpState.Third -> {
                pager?.setCurrentItem(2, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)

            }
            SignUpState.Complete -> {
                pager?.setCurrentItem(3, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
        }
    }
}