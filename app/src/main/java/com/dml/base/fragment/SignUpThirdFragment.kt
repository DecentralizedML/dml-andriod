package com.dml.base.fragment

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.core.content.PermissionChecker
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_third.*

class SignUpThirdFragment : BaseFragment() {

    val REQUEST_CODE_PERMISSION = 0
    var permissionArray: ArrayList<String> = ArrayList()

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpThirdFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_third
    }

    override fun connectViews() {
        smsSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!permissionArray.contains(Manifest.permission.READ_SMS))
                    permissionArray.add(Manifest.permission.READ_SMS)
            } else {
                if (permissionArray.contains(Manifest.permission.READ_SMS))
                    permissionArray.remove(Manifest.permission.READ_SMS)
            }
        }

        cameraRollSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!permissionArray.contains(Manifest.permission.CAMERA))
                    permissionArray.add(Manifest.permission.CAMERA)
            } else {
                if (permissionArray.contains(Manifest.permission.CAMERA))
                    permissionArray.remove(Manifest.permission.CAMERA)
            }
        }

        addressBookSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!permissionArray.contains(Manifest.permission.READ_CONTACTS))
                    permissionArray.add(Manifest.permission.READ_CONTACTS)
            } else {
                if (permissionArray.contains(Manifest.permission.READ_CONTACTS))
                    permissionArray.remove(Manifest.permission.READ_CONTACTS)
            }
        }

        nextBtn?.setOnClickListener { requestPermission() }
    }

    private fun requestPermission() {
        if (permissionArray.size == 0) {
            nextActivity()
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                    permissionArray.toTypedArray(),
                    REQUEST_CODE_PERMISSION
            )
        }
    }

    private fun nextActivity() {
        if (activity is SignUpActivity) {
            (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
        }
    }
}