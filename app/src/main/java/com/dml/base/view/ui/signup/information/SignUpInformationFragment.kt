package com.dml.base.view.ui.signup.information

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dml.base.Configure
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.utils.MarginItemHorizontalDecoration
import com.dml.base.view.adapter.EducationLevelAdapter
import com.dml.base.view.adapter.EducationLevelAdapter.OnItemClickListener
import com.dml.base.view.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_infomation.*
import java.text.SimpleDateFormat
import java.util.*


class SignUpInformationFragment : BaseFragment(), SignUpInformationContract.View {

    private lateinit var presenter: SignUpInformationContract.Presenter

    private var countryDialog: AlertDialog? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var selectedGender: String = ""
    private lateinit var educationLevelAdapter: EducationLevelAdapter

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
        presenter = SignUpInformationPresenter(this, mParentActivity.mService, mCompositeDisposable)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_infomation
    }

    override fun connectViews() {
        skipButton?.setOnClickListener { presenter.onSkipButtonClicked() }
        dateOfBirthEditText?.setOnClickListener { presenter.onDateOfBirthButtonClicked() }
        countryEditText?.setOnClickListener { presenter.onCountryButtonClicked() }
        genderMaleButton?.setOnClickListener { presenter.onMaleButtonClicked(Configure.GENDER_MALE) }
        genderFemaleButton?.setOnClickListener { presenter.onFemaleButtonClicked(Configure.GENDER_FEMALE) }
        genderOtherButton?.setOnClickListener { presenter.onOtherGenderButtonClicked(Configure.GENDER_OTHER) }
        nextButton?.apply {
            setText(R.string.fragment_signup_information_button_next)
            showRightIcon(true)
            setOnClickListener {
                presenter.onNextButtonClicked(
                        firstNameEditText.text.toString()
                        , lastNameEditText.text.toString()
                        , countryEditText.text.toString()
                        , selectedGender
                        , dateOfBirthEditText.text.toString()
                        , Utility.getEducationLevelValue(context, educationLevelAdapter.getSelectedPosition())
                )
            }
        }

        setEducationLevelAdapter()
    }

    override fun setPresenter(presenter: SignUpInformationContract.Presenter) {
        this.presenter = presenter
    }

    private fun setEducationLevelAdapter() {
        val educationLevelAdapter = EducationLevelAdapter(context, object : OnItemClickListener {
            override fun onClick(title: String) {
            }
        })
        educationLevelRecycleView?.adapter = educationLevelAdapter
        educationLevelRecycleView?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        educationLevelRecycleView?.addItemDecoration(MarginItemHorizontalDecoration(resources.getDimension(R.dimen.margin_education_level).toInt()))
    }

    override fun redirectToConnectPage() {
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
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentYear = calendar.get(Calendar.YEAR)

            datePickerDialog = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        val gregorianCalendar = GregorianCalendar(year, monthOfYear, dayOfMonth)
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                        val date = simpleDateFormat.format(gregorianCalendar.time)
                        dateOfBirthEditText?.setText(date)
                    }, currentYear, currentMonth, currentDay)
            datePickerDialog?.datePicker?.maxDate = System.currentTimeMillis()
        }
        datePickerDialog?.show()
    }

    override fun tintMaleButton() {
        genderMaleButton.setBackgroundResource(R.drawable.button_green_border_left_filled)
        genderFemaleButton.setBackgroundResource(0)
        genderOtherButton.setBackgroundResource(0)
        selectedGender = Configure.GENDER_MALE
    }

    override fun tintFemaleButton() {
        genderMaleButton.setBackgroundResource(0)
        genderFemaleButton.setBackgroundResource(R.drawable.button_green_border_center_filled)
        genderOtherButton.setBackgroundResource(0)
        selectedGender = Configure.GENDER_FEMALE
    }

    override fun tintOtherGenderButton() {
        genderMaleButton.setBackgroundResource(0)
        genderFemaleButton.setBackgroundResource(0)
        genderOtherButton.setBackgroundResource(R.drawable.button_green_border_right_filled)
        selectedGender = Configure.GENDER_OTHER
    }

    override fun showIncompleteInformationDialog() {
        Toast.makeText(context, "{incomplete information}", Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        nextButton?.showProgressBar(true)
        nextButton?.isEnabled = false
    }

    override fun dismissProgressBar() {
        nextButton?.showProgressBar(false)
        nextButton?.isEnabled = true
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}