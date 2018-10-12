package com.dml.base

import okhttp3.logging.HttpLoggingInterceptor

class Configure {

    companion object {
        val KEY_ENCRYPT_KEY = "key_encrypt_key"
        val KEY_JWT = "key_jwt"

        val CONNECTION_TIMEOUT_IN_SECOND = 60L
        val CONNECTION_RECONNECTION_IN_SECOND = 20L

        val RETROFIT_LOG_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        val ETHEREUM_ENDPOINT = "https://ropsten.infura.io/v3/35d1fc3bab224128896134a95884dc5c"
        val API_DOMAIN = "https://elegant-brisk-indianjackal.gigalixirapp.com/"
    }
}