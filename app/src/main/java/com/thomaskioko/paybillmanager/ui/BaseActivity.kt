package com.thomaskioko.paybillmanager.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.thomaskioko.paybillmanager.BuildConfig
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        turnOnStrictMode()
    }

    private fun turnOnStrictMode() {
        if (BuildConfig.DEBUG) {
            val strictMode = StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            StrictMode.setThreadPolicy(strictMode)
        }
    }
}