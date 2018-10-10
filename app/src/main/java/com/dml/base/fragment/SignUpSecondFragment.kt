package com.dml.base.fragment

import android.os.Bundle
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_second.*
import android.app.DatePickerDialog
import java.util.*


class SignUpSecondFragment : BaseFragment() {

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
        nextBtn?.setOnClickListener { signUp() }

//        dateOfBirthET?.let {
//            DateInputMask(dateOfBirthET).listen()
//        }

        dateOfBirthET?.setOnClickListener {
            showDatePicker()
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