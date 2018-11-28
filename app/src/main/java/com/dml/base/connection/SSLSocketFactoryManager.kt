package com.dml.base.connection

import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class SSLSocketFactoryManager {

    companion object {

        private var mInstance: SSLSocketFactory? = null
        private var mTrustManager: X509TrustManager? = null

        @Synchronized
        fun getInstance(): SSLSocketFactory? {
            if (mInstance == null) {
                try {
                    val sslContext = SSLContext.getInstance("TLS")
                    sslContext.init(null, getDefaultTrustManager()?.let { arrayOf<TrustManager>(it) }, null)
                    mInstance = sslContext.socketFactory
                } catch (e: NoSuchAlgorithmException) {
                    return null
                } catch (e: KeyManagementException) {
                    return null
                }
            }
            return mInstance
        }

        @Synchronized
        fun getDefaultTrustManager(): X509TrustManager? {
            if (mTrustManager == null) {
                mTrustManager = object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }
                }
            }
            return mTrustManager
        }
    }
}