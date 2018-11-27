package com.dml.base.view.ui.signup

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import com.dml.base.R
import com.dml.base.base.BaseActivity
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.view.adapter.SignUpViewPagerAdapter
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : BaseActivity() {

    sealed class SignUpState {
        object SignUp : SignUpState()
        object Google : SignUpState()
        object SecurityQuestion : SignUpState()
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
            SignUpState.SignUp, SignUpState.Google -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_SIGNUP, true)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                firstProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_empty)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_empty)
                thirdProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFourthImageView?.setImageResource(R.drawable.ic_welcome_empty)
            }
            SignUpState.SecurityQuestion -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_SECURITY_QUESTION, true)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                secondProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_empty)
                thirdProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFourthImageView?.setImageResource(R.drawable.ic_welcome_empty)
            }
            SignUpState.Information -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_INFORMATION, true)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_done)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_ongoing)
                thirdProgressBar.setImageResource(R.color.signup_line_not_finish)
                stageFourthImageView?.setImageResource(R.drawable.ic_welcome_empty)
            }
            SignUpState.Connect -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_CONNECT, true)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_done)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_done)
                thirdProgressBar.setImageResource(R.color.signup_line_finish)
                stageFourthImageView?.setImageResource(R.drawable.ic_welcome_ongoing)

            }
            SignUpState.Complete -> {
                pager?.setCurrentItem(SignUpViewPagerAdapter.PAGE_COMPLETE, true)
                stageFirstImageView?.setImageResource(R.drawable.ic_welcome_done)
                firstProgressBar.setImageResource(R.color.signup_line_finish)
                stageSecondImageView?.setImageResource(R.drawable.ic_welcome_done)
                secondProgressBar.setImageResource(R.color.signup_line_finish)
                stageThirdImageView?.setImageResource(R.drawable.ic_welcome_done)
                thirdProgressBar.setImageResource(R.color.signup_line_finish)
                stageFourthImageView?.setImageResource(R.drawable.ic_welcome_done)
            }
        }
    }

    override fun connectViews() {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_signup
    }
}