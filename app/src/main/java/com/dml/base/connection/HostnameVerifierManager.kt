package com.dml.base.connection

import javax.net.ssl.HostnameVerifier

class HostnameVerifierManager {

    companion object {
        private var mInstance: HostnameVerifier? = null

        @Synchronized
        fun getInstance(): HostnameVerifier {
            if (mInstance == null) {
                mInstance = HostnameVerifier { hostname, sslSession ->
                    //                    return BuildSettings.API_DOMAIN.equals(hostname);
                    true
//                    hostname.equals("graph.facebook.com", ignoreCase = true) ||
//                            hostname.equals("settings.crashlytics.com", ignoreCase = true) ||
//                            hostname.equals("e.crashlytics.com", ignoreCase = true))
                }
            }
            return mInstance as HostnameVerifier
        }
    }
}
