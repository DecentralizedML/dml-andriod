package com.dml.base.view.ui.signup.information

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import com.dml.base.utils.MarginItemHorizontalDecoration
import com.dml.base.view.ui.signup.SignUpActivity
import com.dml.base.view.adapter.EducationLevelAdapter
import com.dml.base.view.adapter.EducationLevelAdapter.OnItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_signup_infomation.*
import java.util.*


class SignUpInformationFragment : BaseFragment(), SignUpInformationContract.View {

    sealed class Gender {
        object Male : Gender()
        object Female : Gender()
        object Other : Gender()
    }

    private lateinit var presenter: SignUpInformationContract.Presenter

    private var countryDialog: AlertDialog? = null
    private var datePickerDialog: DatePickerDialog? = null

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpInformationFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpInformationPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_infomation
    }

    override fun connectViews() {
        skipButton?.setOnClickListener { presenter.onSkipButtonClicked() }
        dateOfBirthEditText?.setOnClickListener { presenter.onDateOfBirthButtonClicked() }
        countryEditText?.setOnClickListener { presenter.onCountryButtonClicked() }
        genderMaleButton?.setOnClickListener { presenter.onMaleButtonClicked(Gender.Male) }
        genderFemaleButton?.setOnClickListener { presenter.onFemaleButtonClicked(Gender.Female) }
        genderOtherButton?.setOnClickListener { presenter.onOtherGenderButtonClicked(Gender.Other) }
        nextButton?.apply {
            setText(R.string.activity_signup_information_button_next)
            showRightIcon(true)
            setOnClickListener {
                presenter.onNextButtonClicked(
                        fullNameEditText.text.toString()
                        , countryEditText.text.toString()
                        , dateOfBirthEditText.text.toString()
                        , "educationLevelTemp"
                )
            }
        }

        setEducationLevelAdapter()
    }

    override fun setPresenter(presenter: SignUpInformationContract.Presenter) {
        this.presenter = presenter
    }

    private fun setEducationLevelAdapter() {
        val adapter = EducationLevelAdapter(context, object : OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        educationLevelRecycleView?.adapter = adapter
        educationLevelRecycleView?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        educationLevelRecycleView?.addItemDecoration(MarginItemHorizontalDecoration(resources.getDimension(R.dimen.margin_education_level).toInt()))
    }

    override fun redirectToSignUpConnect() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Connect)
    }

    override fun showCountryDialog() {
        if (countryDialog == null) {
            val locales = Locale.getISOCountries()
            val countryNameList: Array<String?> = arrayOfNulls(locales.size)

            for (i in 0 until locales.size) {
                val obj = Locale("", locales[i])
                countryNameList[i] = obj.displayCountry
            }

            val builder = AlertDialog.Builder(context)
            builder.setItems(countryNameList) { _, which ->
                countryEditText?.setText(countryNameList[which])
            }

            countryDialog = builder.create()
        }
        countryDialog?.show()
    }

    override fun showDatePicker() {
        if (datePickerDialog == null) {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            datePickerDialog = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        dateOfBirthEditText?.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    }, year, month, day)
            datePickerDialog?.datePicker?.maxDate = System.currentTimeMillis()
        }
        datePickerDialog?.show()
    }

    override fun tintMaleButton() {
        genderMaleButton.setBackgroundResource(R.drawable.button_green_border_left_filled)
        genderFemaleButton.setBackgroundResource(0)
        genderOtherButton.setBackgroundResource(0)
    }

    override fun tintFemaleButton() {
        genderMaleButton.setBackgroundResource(0)
        genderFemaleButton.setBackgroundResource(R.drawable.button_green_border_center_filled)
        genderOtherButton.setBackgroundResource(0)
    }

    override fun tintOtherGenderButton() {
        genderMaleButton.setBackgroundResource(0)
        genderFemaleButton.setBackgroundResource(0)
        genderOtherButton.setBackgroundResource(R.drawable.button_green_border_right_filled)
    }

    override fun showIncompleteInformationDialog() {
        Toast.makeText(context, "{incomplete information}", Toast.LENGTH_SHORT).show()
    }

    override fun updateUserRequest() {
        val signUpRequestModel = UserSignUpRequest()
        signUpRequestModel.apply {
            user.apply {
                //                email = emailEditText?.text.toString()
//                password = passwordEditText?.text.toString()
//                passwordConfirmation = passwordEditText?.text.toString()
                firstName = fullNameEditText?.text.toString()
                lastName = fullNameEditText?.text.toString()
            }
        }

        mParentActivity?.mService?.updateUserRequest(signUpRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserSignUpResponse>(context) {
                    override fun onNext(modelUser: UserSignUpResponse) {
                        Preferences.setJWT(context, modelUser.jwt)
                        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Connect)
                    }

                    override fun onComplete() {
                        super.onComplete()
                        nextButton?.showProgressBar(false)
                        nextButton?.isEnabled = true
                    }

                    override fun onStart() {
                        super.onStart()
                        nextButton?.showProgressBar(true)
                        nextButton?.isEnabled = false
                    }
                })?.let { mCompositeDisposable.add(it) }
    }
}