package com.dml.base

import android.text.TextUtils
import android.util.Base64
import java.security.KeyFactory
import java.security.KeyStore
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

class Crypto {
    val KEYSTORE_PROVIDER = "AndroidKeyStore"
    val KEY_ALIAS_RSA = "key_rsa"
    val KEY_ALGORITHM_RSA = "RSA"
    val RSA_MODE = "RSA/ECB/PKCS1Padding"
    val mKeyStore: KeyStore? = null

    companion object {
        fun getRSAPublicKeyFromString(publicKeyPEM: String): PublicKey {
            var publicKeyPEM = stripPublicKeyHeaders(publicKeyPEM)
            val keyFactory = KeyFactory.getInstance("RSA", "SC")
            val publicKeyBytes = Base64.decode(publicKeyPEM.toByteArray(charset("UTF-8")), Base64.DEFAULT)
            val x509KeySpec = X509EncodedKeySpec(publicKeyBytes)

            return keyFactory.generatePublic(x509KeySpec)
        }

        fun stripPublicKeyHeaders(key: String): String {
            val strippedKey = StringBuilder()
            val lines = key.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (line in lines) {
                if (!line.contains("BEGIN PUBLIC KEY") && !line.contains("END PUBLIC KEY") && !TextUtils.isEmpty(line.trim { it <= ' ' })) {
                    strippedKey.append(line.trim { it <= ' ' })
                }
            }

            return strippedKey.toString().trim { it <= ' ' }
        }
    }
}