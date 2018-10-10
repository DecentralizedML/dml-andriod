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
    private val mInstance: SSLSocketFactory? = null
    private var mTrustManager: X509TrustManager? = null

    @Synchronized
    fun getInstance(): SSLSocketFactory? {
        if (mInstance == null) {
            val context: SSLContext
            return try {
                context = SSLContext.getInstance("TLS")
                context.init(null, getDefaultTrustManager()?.let { arrayOf<TrustManager>(it) }, null)
                context.socketFactory
            } catch (e: NoSuchAlgorithmException) {
                null
            } catch (e: KeyManagementException) {
                null
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
                    //                    return null;
                    return arrayOfNulls(0)
                }
            }
        }
        return mTrustManager
    }
}