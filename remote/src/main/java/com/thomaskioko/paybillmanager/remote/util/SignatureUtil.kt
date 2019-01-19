package com.thomaskioko.paybillmanager.remote.util

import android.util.Base64
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec


object SignatureUtil {

    fun generateSignature(dataStringToSign: String, privateKey: String): String {

        var base64Signature = ""

        try {
            val rsaPrivateKey = getPrivateKeyFromString(privateKey)
            //Sign
            base64Signature = sign(rsaPrivateKey, dataStringToSign)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }

        return base64Signature

    }


    /**
     * Create RSAPrivateKey Object from the Private Key String using PKCS8EncodedKeySpec
     */
    @Throws(IOException::class, GeneralSecurityException::class)
    private fun getPrivateKeyFromString(privateKey: String): RSAPrivateKey {

        privateKey.replace("-----BEGIN PRIVATE KEY-----\n", "")
        privateKey.replace("-----END PRIVATE KEY-----", "")
        val encoded = Base64.decode(privateKey, Base64.DEFAULT)
        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = PKCS8EncodedKeySpec(encoded)
        return keyFactory.generatePrivate(keySpec) as RSAPrivateKey
    }

    /**
     * Create base64 encoded signature using SHA256/RSA.
     */
    @Throws(
            NoSuchAlgorithmException::class,
            InvalidKeyException::class,
            SignatureException::class,
            UnsupportedEncodingException::class
    )
    private fun sign(privateKey: PrivateKey, message: String): String {
        val sign = Signature.getInstance("SHA256withRSA")
        sign.initSign(privateKey)
        sign.update(message.toByteArray(charset("UTF-8")))
        return Base64.encodeToString(sign.sign(), Base64.DEFAULT)
    }
}