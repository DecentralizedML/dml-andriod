package com.dml.base

import android.content.Context
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.Key
import javax.crypto.Cipher

class RSA {
    private fun encryptToBase64(publicKey: Key, toBeCiphered: String): String {
        try {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)

            val dataBytes = ByteArrayInputStream(toBeCiphered.toByteArray(charset("UTF-8")))
            val encrypted = ByteArrayOutputStream()

            while (dataBytes.available() > 0) {
                val buffer = ByteArray(
                        if (dataBytes.available() < cipher.blockSize)
                            dataBytes.available()
                        else
                            cipher.blockSize)
                dataBytes.read(buffer)
                encrypted.write(cipher.doFinal(buffer))
            }

            return Base64.encodeToString(encrypted.toByteArray(), Base64.NO_WRAP)
        } catch (e: Exception) {
        }

        return ""
    }

    private fun encryptWithKey(key: String, text: String): String {
        return try {
            val apiPublicKey = Crypto.getRSAPublicKeyFromString(key)
            encryptToBase64(apiPublicKey, text)
        } catch (e: Exception) {
            ""
        }
    }

    fun encryptWithStoredKey(context: Context, text: String): String {
        return encryptWithKey(Preferences.getRequestEncryptKey(context), text)
    }

    fun encryptWithStoredKey(context: Context, toBeCiphered: ByteArray): ByteArray? {
        try {
            val key = Crypto.getRSAPublicKeyFromString(Preferences.getRequestEncryptKey(context))

            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)

            val dataBytes = ByteArrayInputStream(toBeCiphered)
            val encrypted = ByteArrayOutputStream()

            while (dataBytes.available() > 0) {
                val buffer = ByteArray(
                        if (dataBytes.available() < cipher.blockSize)
                            dataBytes.available()
                        else
                            cipher.blockSize)
                dataBytes.read(buffer)
                encrypted.write(cipher.doFinal(buffer))
            }

            return encrypted.toByteArray()
        } catch (e: Exception) {
            return null
        }
    }
}