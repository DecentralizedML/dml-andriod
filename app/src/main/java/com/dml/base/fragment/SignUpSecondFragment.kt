package com.dml.base.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_second.*
import java.util.*


class SignUpSecondFragment : BaseFragment() {

    sealed class Gender {
        object Male : Gender()
        object Female : Gender()
        object Other : Gender()
    }

    private var countryDialog: AlertDialog? = null
    private var gender: Gender? = null

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpSecondFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_second
    }

    override fun connectViews() {
        nextBtn?.apply {
            setText(R.string.activity_signup_second_button_next)
            showRightIcon(true)
            setOnClickListener { signUp() }
        }
//        dateOfBirthET?.let {
//            DateInputMask(dateOfBirthET).listen()
//        }

        dateOfBirthET?.setOnClickListener {
            showDatePicker()
        }

        countryET?.setOnClickListener {
            showCountryDialog()
        }

        genderMaleBtn?.setOnClickListener {
            gender = Gender.Male
            genderMaleBtn.setBackgroundResource(R.drawable.button_blue_border_left_fill)
            genderFemaleBtn.setBackgroundResource(0)
            genderOtherBtn.setBackgroundResource(0)
        }

        genderFemaleBtn?.setOnClickListener {
            gender = Gender.Female
            genderMaleBtn.setBackgroundResource(0)
            genderFemaleBtn.setBackgroundResource(R.drawable.button_blue_border_center_fill)
            genderOtherBtn.setBackgroundResource(0)
        }

        genderOtherBtn?.setOnClickListener {
            gender = Gender.Other
            genderMaleBtn.setBackgroundResource(0)
            genderFemaleBtn.setBackgroundResource(0)
            genderOtherBtn.setBackgroundResource(R.drawable.button_blue_border_right_fill)
        }
    }

    private fun showCountryDialog() {
        if (countryDialog != null) {
            countryDialog?.show()
        } else {
            val locales = Locale.getISOCountries()
            val countryNameList: Array<String?> = arrayOfNulls(locales.size)

            for (i in 0 until locales.size) {
                val obj = Locale("", locales[i])
                countryNameList[i] = obj.displayCountry
            }

            val builder = AlertDialog.Builder(context)
            builder.setItems(countryNameList) { _, which ->
                countryET?.setText(countryNameList[which])
            }

            countryDialog = builder.create()
            countryDialog?.show()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    dateOfBirthET?.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                }, year, month, day)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun signUp() {
//        if (!Utility.isValidEmail(emailET?.text.toString()))
//            Toast.makeText(activity, "valid", Toast.LENGTH_SHORT).show()
//
        if (activity is SignUpActivity) {
            (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Third)
        }
    }
}