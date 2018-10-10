package com.dml.base.connection

import java.net.CookieManager
import java.net.CookieStore

class CookieManagerClient {

    companion object {
        private var mInstance: CookieManager? = null

        @Synchronized
        fun getInstance(): CookieManager {
            if (mInstance == null) {
                mInstance = CookieManager()
            }
            return mInstance as CookieManager
        }

        fun getCookiesStore(): CookieStore {
            return getInstance().cookieStore
        }

        fun removeCookies() {
            getInstance().cookieStore.removeAll()
        }
    }

}