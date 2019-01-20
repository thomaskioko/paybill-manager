package com.thomaskioko.paybillmanager.mobile.util

import android.content.Context
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject

open class FileUtils @Inject constructor(val context: Context) {

    fun loadFile(): String? {
        return try {
            val inputStream: InputStream = context.assets.open("pem/pkcs8_privatekey.pem")
            inputStream.bufferedReader().use { it.readText() }.toString()
        } catch (exception: Exception) {
            Timber.e("@loadFile $exception ")
            null
        }
    }
} 