package com.dml.base.view.activity

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.R
import com.dml.base.view.adapter.SignUpViewPagerAdapter
import com.dml.base.base.BaseActivity
import com.dml.base.network.model.UserSignUpRequest
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : BaseActivity() {

    sealed class SignUpState {
        object SignUp : SignUpState()
        object Google : SignUpState()
        object Information : SignUpState()
        object Connect : SignUpState()
        object Complete : SignUpState()
    }

    private var viewPagerAdapter: FragmentPagerAdapter? = null
    private var userSignUpRequest: UserSignUpRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPagerAdapter = SignUpViewPagerAdapter(supportFragmentManager)
        pager?.adapter = viewPagerAdapter
        pager?.offscreenPageLimit = 0
        setState(SignUpState.SignUp)
    }

    fun updateUserSignUpRequestModel(userSignUpRequest: UserSignUpRequest) {
        this.userSignUpRequest = userSignUpRequest
    }

    fun getUserSignUpRequestModel(): UserSignUpRequest? {
        return userSignUpRequest
    }

    fun setState(state: SignUpState) {
        when (state) {
            SignUpState.SignUp -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_SIGNUP, true)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                stageSecondImageView?.setImageResource(0)
                stageThirdImageView?.setImageResource(0)
            }
            SignUpState.Google -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_GOOGLE, true)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                stageSecondImageView?.setImageResource(0)
                stageThirdImageView?.setImageResource(0)
            }
            SignUpState.Information -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_INFORMATION, true)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                stageThirdImageView?.setImageResource(0)
            }
            SignUpState.Connect -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_CONNECT, true)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_done)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_ongoing)

            }
            SignUpState.Complete -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_COMPLETE, true)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_done)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_done)
            }
        }
    }

    override fun connectViews() {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_signup
    }
}