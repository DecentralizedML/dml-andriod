package com.dml.base.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_connect.*

class SignUpConnectFragment : BaseFragment() {

    private val REQUEST_CODE_PERMISSION = 1001
    private val permissionArray: ArrayList<String> = ArrayList()

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpConnectFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_connect
    }

    override fun connectViews() {
        smsBtn?.setOnClickListener {
            if (permissionArray.contains(Manifest.permission.READ_SMS)) {
                permissionArray.remove(Manifest.permission.READ_SMS)
                smsBtn.setImageResource(R.drawable.bg_circle)
            } else {
                permissionArray.add(Manifest.permission.READ_SMS)
                smsBtn.setImageResource(R.drawable.bg_circle_filled)
            }
        }

        cameraBtn?.setOnClickListener {
            if (permissionArray.contains(Manifest.permission.CAMERA)) {
                permissionArray.remove(Manifest.permission.CAMERA)
                cameraBtn.setImageResource(R.drawable.bg_circle)
            } else {
                permissionArray.add(Manifest.permission.CAMERA)
                cameraBtn.setImageResource(R.drawable.bg_circle_filled)
            }
        }

        addressBookBtn?.setOnClickListener {
            if (permissionArray.contains(Manifest.permission.READ_CONTACTS)) {
                permissionArray.remove(Manifest.permission.READ_CONTACTS)
                addressBookBtn.setImageResource(R.drawable.bg_circle)
            } else {
                permissionArray.add(Manifest.permission.READ_CONTACTS)
                addressBookBtn.setImageResource(R.drawable.bg_circle_filled)
            }
        }

        nextBtn?.apply {
            setText(R.string.activity_signup_information_button_next)
            showRightIcon(true)
            setOnClickListener { requestPermission() }
        }
    }

    private fun requestPermission() {
        if (permissionArray.size == 0 || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            goNextStep()
            return
        }

        requestPermissions(permissionArray.toTypedArray(), REQUEST_CODE_PERMISSION)
    }

    private fun goNextStep() {
        (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Denied permission", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Granted permission", Toast.LENGTH_SHORT).show()
                    goNextStep()
                }
                return
            }
        }
    }
}