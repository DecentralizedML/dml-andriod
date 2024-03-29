package com.dml.base.view.ui.settings.profile

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dml.base.Configure
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.utils.MarginItemHorizontalDecoration
import com.dml.base.view.adapter.EducationLevelAdapter
import com.dml.base.view.dialog.GeneralMessageDialog
import kotlinx.android.synthetic.main.fragment_settings_profile.*
import java.text.SimpleDateFormat
import java.util.*

class SettingsProfileFragment : BaseFragment(), SettingsProfileContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsProfileFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SettingsProfileContract.Presenter

    private var countryDialog: AlertDialog? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var selectedGender: String = ""
    private lateinit var educationLevelAdapter: EducationLevelAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SettingsProfilePresenter(this, mParentActivity.mService, mParentActivity.mCompositeDisposable)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_profile
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings_profile)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
        }

        dateOfBirthEditText?.setOnClickListener { presenter.onDateOfBirthButtonClicked() }
        countryEditText?.setOnClickListener { presenter.onCountryButtonClicked() }
        genderMaleButton?.setOnClickListener { presenter.onMaleButtonClicked(Configure.GENDER_MALE) }
        genderFemaleButton?.setOnClickListener { presenter.onFemaleButtonClicked(Configure.GENDER_FEMALE) }
        genderOtherButton?.setOnClickListener { presenter.onOtherGenderButtonClicked(Configure.GENDER_OTHER) }
        updateButton?.apply {
            setText(R.string.settings_profile_button_update)
            setOnClickListener {
                presenter.onUpdateButtonClicked(
                        firstNameEditText.text.toString()
                        , lastNameEditText.text.toString()
                        , countryEditText.text.toString()
                        , dateOfBirthEditText.text.toString()
                        , selectedGender
                        , Utility.getEducationLevelValue(context, educationLevelAdapter.getSelectedPosition())
                )
            }
        }

        setEducationLevelAdapter()
    }

    override fun setPresenter(presenter: SettingsProfileContract.Presenter) {
        this.presenter = presenter
    }

    private fun setEducationLevelAdapter() {
        educationLevelAdapter = EducationLevelAdapter(context, object : EducationLevelAdapter.OnItemClickListener {
            override fun onClick(title: String) {
            }
        })
        educationLevelRecycleView?.adapter = educationLevelAdapter
        educationLevelRecycleView?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        educationLevelRecycleView?.addItemDecoration(MarginItemHorizontalDecoration(resources.getDimension(R.dimen.margin_education_level).toInt()))
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
    }

    override fun showUpdateSuccessDialog() {
        GeneralMessageDialog().builder()
                .title("{Update Profile Success}")
                .rightButtonText("{OK}")
                .build()
                .show(fragmentManager, "")
    }

    override fun showProgressBar() {
        updateButton?.showProgressBar(true)
        updateButton?.isEnabled = false
    }

    override fun dismissProgressBar() {
        updateButton?.showProgressBar(false)
        updateButton?.isEnabled = true
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}