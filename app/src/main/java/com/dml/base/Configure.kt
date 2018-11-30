package com.dml.base

import okhttp3.logging.HttpLoggingInterceptor

class Configure {

    companion object {
        const val KEY_ENCRYPT_KEY = "key_encrypt_key"
        const val KEY_JWT = "key_jwt"

        const val CONNECTION_TIMEOUT_IN_SECOND = 60L
        const val CONNECTION_RECONNECTION_IN_SECOND = 20L

        val RETROFIT_LOG_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        const val ETHEREUM_ENDPOINT = "https://ropsten.infura.io/v3/35d1fc3bab224128896134a95884dc5c"
        const val API_DOMAIN = "https://dml-api.dev.kyokan.io/"

        const val GENDER_MALE = "male"
        const val GENDER_FEMALE = "female"
        const val GENDER_OTHER = "other"
    }
}