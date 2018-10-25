package com.dml.base.connection

import android.content.Context
import com.dml.base.Configure
import com.dml.base.Preferences
import com.dml.base.Utility
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {

    companion object {

        val TAG = RetrofitClient::class.java.simpleName

        private var mRetrofit: Retrofit? = null
        private var mHeaderInterceptor: Interceptor? = null

        //                    .certificatePinner(certificatePinner)
        //                    .sslSocketFactory(SSLSocketFactoryManager.getInstance(), SSLSocketFactoryManager.getDefaultTrustManager())

        @Synchronized
        fun getDefaultInstance(context: Context): Retrofit {
            if (mRetrofit == null) {
                val logger = HttpLoggingInterceptor()
                logger.level = Configure.RETROFIT_LOG_LEVEL

                val okHttpClientBuilder = OkHttpClient.Builder()
                        .cookieJar(JavaNetCookieJar(CookieManagerClient.getInstance()))
                        .hostnameVerifier(HostnameVerifierManager.getInstance())
                        .addInterceptor(getDefaultHeaderInterceptor(context))
                        .retryOnConnectionFailure(true)
                        .readTimeout(Configure.CONNECTION_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                        .connectTimeout(Configure.CONNECTION_RECONNECTION_IN_SECOND, TimeUnit.SECONDS)

//                if (!BuildSettings.DEV) {
//                    val certificatePinner = CertificatePinner.Builder().add("{package_name}", BuildSettings.SHA_KEY).build()
//                    okHttpClientBuilder.certificatePinner(certificatePinner)
                okHttpClientBuilder.addInterceptor(logger)
//                }


                mRetrofit = Retrofit.Builder()
                        .baseUrl(Configure.API_DOMAIN)
                        .client(okHttpClientBuilder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return mRetrofit as Retrofit
        }

        @Synchronized
        fun getDefaultHeaderInterceptor(context: Context): Interceptor? {
            if (mHeaderInterceptor == null) {
                mHeaderInterceptor = Interceptor { chain ->
                    val original = chain.request()

                    val builder = original.newBuilder()
                    builder.header("Content-Type", "application/x-www-form-urlencoded")

                    if (Utility.isLoggedIn(context))
                        builder.header("Authorization", Preferences.getJWT(context))

                    builder.build()

                    val request = builder.build()

                    chain.proceed(request)
                }
            }
            return mHeaderInterceptor
        }
    }
}