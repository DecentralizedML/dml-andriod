package com.dml.base.activity

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.core.content.ContextCompat
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
        setState(SignUpState.First)
    }

    public fun setState(state: SignUpState) {
        when (state) {
            SignUpState.First -> {
                pager?.setCurrentItem(0, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setImageResource(R.drawable.ic_loading)
                stageSecondIV?.setImageResource(0)
                stageThirdIV?.setImageResource(0)
            }
            SignUpState.Second -> {
                pager?.setCurrentItem(1, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line_light), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setImageResource(R.drawable.ic_tick)
                stageSecondIV?.setImageResource(R.drawable.ic_loading)
                stageThirdIV?.setImageResource(0)
            }
            SignUpState.Third -> {
                pager?.setCurrentItem(2, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setImageResource(R.drawable.ic_tick)
                stageSecondIV?.setImageResource(R.drawable.ic_tick)
                stageThirdIV?.setImageResource(R.drawable.ic_loading)

            }
            SignUpState.Complete -> {
                pager?.setCurrentItem(3, true)
                firstProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                secondProgressBar?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageFirstIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageSecondIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
//                stageThirdIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageSecondBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageThirdBackgroundIV?.setColorFilter(ContextCompat.getColor(this@SignUpActivity, R.color.signup_line), android.graphics.PorterDuff.Mode.MULTIPLY)
                stageFirstIV?.setImageResource(R.drawable.ic_tick)
                stageSecondIV?.setImageResource(R.drawable.ic_tick)
                stageThirdIV?.setImageResource(R.drawable.ic_tick)
            }
        }
    }
}