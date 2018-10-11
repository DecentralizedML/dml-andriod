package com.dml.base

import okhttp3.logging.HttpLoggingInterceptor

class Configure {

    companion object {
        val KEY_ENCRYPT_KEY = "key_encrypt_key"
        val KEY_JWT = "key_jwt"

        val CONNECTION_TIMEOUT_IN_SECOND = 60L
        val CONNECTION_RECONNECTION_IN_SECOND = 20L

        val RETROFIT_LOG_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        val API_DOMAIN = "https://elegant-brisk-indianjackal.gigalixirapp.com/"
    }
}