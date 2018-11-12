package com.dml.base

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.Key
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.security.auth.x500.X500Principal

class SecuredPreferences : SharedPreferences {
    companion object {
        val TAG = SecuredPreferences::class.java.simpleName
        val PROVIDER_KEY_STORE = "AndroidKeyStore"
        val AES_MODE = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) "AES/GCM/NoPadding" else "AES/ECB/PKCS7Padding"
        val RSA_MODE = "RSA/ECB/PKCS1Padding"
        val KEY_ALIAS = "preference_encrypt_key"
        val AES_KEY_ALIAS = "aes_key"
        val AES_PROVIDER = "BC"
        val AES_IV_LENGTH = 12
        var mInstance: SecuredPreferences? = null
        var mPreferences: SharedPreferences? = null
        var mErrorListener: OnPreferencesErrorListener? = null
        var mKeyStore: KeyStore? = null
        var mKey: Key? = null
        val iv = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        interface OnPreferencesErrorListener {
            fun onPreferenceError(e: Exception)
        }

        @Synchronized
        fun getInstance(context: Context): SecuredPreferences {
            return getInstance(context, null)
        }

        @Synchronized
        fun getInstance(context: Context, errorListener: OnPreferencesErrorListener?): SecuredPreferences {
            if (mInstance == null) {
                mInstance = SecuredPreferences()
                mPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
                mErrorListener = errorListener
                if (mErrorListener == null) {
                    mErrorListener = object : OnPreferencesErrorListener {
                        override fun onPreferenceError(e: Exception) {
                            Utility.Error(TAG, e.toString())
                        }
                    }
                }

                try {
                    mKeyStore = KeyStore.getInstance(PROVIDER_KEY_STORE)
                    mKeyStore?.load(null)
                    mKeyStore?.let {
                        if (!mKeyStore!!.containsAlias(KEY_ALIAS)) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER_KEY_STORE)

                                keyGenerator.init(KeyGenParameterSpec.Builder(
                                        KEY_ALIAS,
                                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                                        .setRandomizedEncryptionRequired(false)
                                        .build())
                                mKey = keyGenerator.generateKey()

                            } else {
                                // Generate RSA Key
                                val start = Calendar.getInstance()
                                val end = Calendar.getInstance()
                                end.add(Calendar.YEAR, 80)
                                val spec = KeyPairGeneratorSpec.Builder(context)
                                        .setAlias(KEY_ALIAS)
                                        .setSubject(X500Principal("CN=$KEY_ALIAS"))
                                        .setSerialNumber(BigInteger.TEN)
                                        .setStartDate(start.time)
                                        .setEndDate(end.time)
                                        .build()
                                val keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER_KEY_STORE)
                                keyPairGenerator.initialize(spec)
                                keyPairGenerator.generateKeyPair()

                                // Generate AES Key
                                var encryptedKeyB64 = mPreferences?.getString(AES_KEY_ALIAS, null)
                                if (encryptedKeyB64 == null) {
                                    val key = ByteArray(16)
                                    val secureRandom = SecureRandom()
                                    secureRandom.nextBytes(key)
                                    val encryptedKey = encryptAesKey(key)
                                    encryptedKeyB64 = Base64.encodeToString(encryptedKey, Base64.NO_WRAP)
                                    mPreferences?.edit()
                                            ?.putString(AES_KEY_ALIAS, encryptedKeyB64)
                                            ?.apply()

                                }

                                mKey = SecretKeySpec(decryptAesKey(Base64.decode(encryptedKeyB64, Base64.NO_WRAP)), "AES")
                            }
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                mKey = mKeyStore?.getKey(KEY_ALIAS, null)
                            } else {
                                val encryptedKeyB64 = mPreferences?.getString(AES_KEY_ALIAS, null)
                                mKey = SecretKeySpec(decryptAesKey(Base64.decode(encryptedKeyB64, Base64.NO_WRAP)), "AES")
                            }
                        }
                    }
                } catch (e: Exception) {
                    Utility.Error(TAG, e.toString())
                    mErrorListener?.onPreferenceError(e)
                }

            }
            return mInstance as SecuredPreferences
        }

        private fun encrypt(data: String, key: Boolean): String {
            try {
                val c: Cipher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    c = Cipher.getInstance(AES_MODE)
                    if (key) {
                        c.init(Cipher.ENCRYPT_MODE, mKey, GCMParameterSpec(128, iv))
                    } else {
                        c.init(Cipher.ENCRYPT_MODE, mKey)
                    }
                } else {
                    c = Cipher.getInstance(AES_MODE, AES_PROVIDER)
                    c.init(Cipher.ENCRYPT_MODE, mKey)
                }

                val encodedBytes = c.doFinal(data.toByteArray())

                val outputStream = ByteArrayOutputStream()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    outputStream.write(c.iv)
                }
                outputStream.write(encodedBytes)

                return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
            } catch (e: Exception) {
                //            mErrorListener.onPreferenceError(e);
                return ""
            }
        }

        private fun decrypt(data: String): String {
            try {
                val cipher: Cipher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cipher = Cipher.getInstance(AES_MODE)
                } else {
                    cipher = Cipher.getInstance(AES_MODE, AES_PROVIDER)
                    cipher.init(Cipher.DECRYPT_MODE, mKey)
                }

                val decodedBytes = Base64.decode(data.toByteArray(), Base64.NO_WRAP)
                val inputStream = ByteArrayInputStream(decodedBytes)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val iv = ByteArray(AES_IV_LENGTH)
                    inputStream.read(iv, 0, AES_IV_LENGTH)
                    cipher.init(Cipher.DECRYPT_MODE, mKey, GCMParameterSpec(128, iv))
                }

                val dataBytes = ByteArray(inputStream.available())
                inputStream.read(dataBytes)

                return String(cipher.doFinal(dataBytes), charset("UTF-8"))
            } catch (e: Exception) {
                //            mErrorListener.onPreferenceError(e);
                return ""
            }
        }


        /**
         * For Pre-Android M only, use for encrypt the AES key before storing in shared preferences.
         */
        @Throws(Exception::class)
        private fun encryptAesKey(secret: ByteArray): ByteArray {
            val privateKeyEntry = mKeyStore?.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
            // Encrypt the text
            val inputCipher = Cipher.getInstance(RSA_MODE, "AndroidOpenSSL")
            inputCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.certificate.publicKey)

            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(outputStream, inputCipher)
            cipherOutputStream.write(secret)
            cipherOutputStream.close()

            return outputStream.toByteArray()
        }

        /**
         * For Pre-Android M only, use for encrypt the AES key before getting from shared preferences.
         */
        @Throws(Exception::class)
        private fun decryptAesKey(encrypted: ByteArray): ByteArray {
            val privateKeyEntry = mKeyStore?.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
            val output = Cipher.getInstance(RSA_MODE, "AndroidOpenSSL")
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.privateKey)
            val cipherInputStream = CipherInputStream(ByteArrayInputStream(encrypted), output)
            val values = ArrayList<Byte>()
            var nextByte = cipherInputStream.read()
            while (nextByte != -1) {
                values.add(nextByte.toByte())
                nextByte = cipherInputStream.read()
            }

            val bytes = ByteArray(values.size)
            for (i in bytes.indices) {
                bytes[i] = values[i]
            }
            return bytes
        }
    }

    override fun getAll(): Map<String, String> {
        val encryptedMap = mPreferences?.getAll()
        val decryptedMap = HashMap<String, String>(encryptedMap!!.size)
        for ((key, value) in encryptedMap) {
            if (AES_KEY_ALIAS == key) {
                continue
            }

            try {
                decryptedMap[decrypt(key)] = decrypt(value.toString())
            } catch (e: Exception) {
                // Ignore unencrypted key/value pairs
            }

        }
        return decryptedMap
    }

    override fun getString(key: String, defaultValue: String): String {
        val ekey = encrypt(key, true)
        val encryptedValue = mPreferences?.getString(ekey, null)
        return if (encryptedValue != null) decrypt(encryptedValue) else defaultValue
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun getStringSet(key: String, defaultValues: Set<String>?): Set<String>? {
        val encryptedSet = mPreferences?.getStringSet(encrypt(key, true), null)
                ?: return defaultValues
        val decryptedSet = HashSet<String>(encryptedSet.size)
        for (encryptedValue in encryptedSet) {
            decryptedSet.add(decrypt(encryptedValue))
        }
        return decryptedSet
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        val encryptedValue = mPreferences?.getString(encrypt(key, true), null)
                ?: return defaultValue
        try {
            return Integer.parseInt(decrypt(encryptedValue))
        } catch (e: NumberFormatException) {
            return defaultValue
        }

    }

    override fun getLong(key: String, defaultValue: Long): Long {
        val encryptedValue = mPreferences?.getString(encrypt(key, true), null)
                ?: return defaultValue
        try {
            return java.lang.Long.parseLong(decrypt(encryptedValue))
        } catch (e: NumberFormatException) {
            return defaultValue
        }

    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        val encryptedValue = mPreferences?.getString(encrypt(key, true), null)
                ?: return defaultValue
        try {
            return java.lang.Float.parseFloat(decrypt(encryptedValue))
        } catch (e: NumberFormatException) {
            return defaultValue
        }

    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val encryptedValue = mPreferences?.getString(encrypt(key, true), null)
                ?: return defaultValue

        try {
            val str = decrypt(encryptedValue)
            return java.lang.Boolean.parseBoolean(str)
        } catch (e: NumberFormatException) {
            return defaultValue
        }

    }

    override operator fun contains(key: String): Boolean {
        return mPreferences?.contains(encrypt(key, true))!!
    }

    override fun edit(): Editor {
        return Editor()
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        mPreferences?.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        mPreferences?.unregisterOnSharedPreferenceChangeListener(listener)
    }

    class Editor : SharedPreferences.Editor {
        private val mEditor = mPreferences?.edit()

        override fun putString(key: String, value: String): SharedPreferences.Editor {
            mEditor?.putString(SecuredPreferences.encrypt(key, true), SecuredPreferences.encrypt(value, false))
            return this
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        override fun putStringSet(key: String, values: Set<String>): SharedPreferences.Editor {
            val encryptedValues = HashSet<String>(values.size)
            for (value in values) {
                encryptedValues.add(SecuredPreferences.encrypt(value, false))
            }
            mEditor?.putStringSet(SecuredPreferences.encrypt(key, true), encryptedValues)
            return this
        }

        override fun putInt(key: String, value: Int): SharedPreferences.Editor {
            mEditor?.putString(SecuredPreferences.encrypt(key, true), SecuredPreferences.encrypt(value.toString(), false))
            return this
        }

        override fun putLong(key: String, value: Long): SharedPreferences.Editor {
            mEditor?.putString(SecuredPreferences.encrypt(key, true), SecuredPreferences.encrypt(value.toString(), false))
            return this
        }

        override fun putFloat(key: String, value: Float): SharedPreferences.Editor {
            mEditor?.putString(SecuredPreferences.encrypt(key, true), SecuredPreferences.encrypt(value.toString(), false))
            return this
        }

        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
            mEditor?.putString(SecuredPreferences.encrypt(key, true), SecuredPreferences.encrypt(value.toString(), false))
            return this
        }

        override fun remove(key: String): SharedPreferences.Editor {
            mEditor?.remove(SecuredPreferences.encrypt(key, true))
            return this
        }

        override fun clear(): SharedPreferences.Editor {
            mEditor?.clear()
            return this
        }

        override fun commit(): Boolean {
            mEditor?.let {
                return mEditor.commit()
            }
            return false
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        override fun apply() {
            mEditor?.apply()
        }
    }
}