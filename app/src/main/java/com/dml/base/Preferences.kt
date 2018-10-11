package com.dml.base

import android.content.Context
import com.dml.base.Configure

class Preferences {

    companion object {

        private fun getPreferences(context: Context): SecuredPreferences {
            return SecuredPreferences.getInstance(context)
        }

        fun setRequestEncryptKey(context: Context, key: String) {
            getPreferences(context).edit().putString(Configure.KEY_ENCRYPT_KEY, key).commit()
        }

        fun getRequestEncryptKey(context: Context): String {
            return getPreferences(context).getString(Configure.KEY_ENCRYPT_KEY, "")
        }

        fun setJWT(context: Context, key: String) {
            getPreferences(context).edit().putString(Configure.KEY_JWT, key).commit()
        }

        fun getSWT(context: Context): String {
            return getPreferences(context).getString(Configure.KEY_JWT, "")
        }
    }
}