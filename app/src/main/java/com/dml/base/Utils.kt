package com.dml.base

import android.util.Patterns

class Utils {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}