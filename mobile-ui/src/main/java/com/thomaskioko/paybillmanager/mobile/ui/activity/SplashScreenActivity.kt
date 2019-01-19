package com.thomaskioko.paybillmanager.mobile.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.util.AppThemes
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity(), HasActivityInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(getSavedTheme())
        super.onCreate(savedInstanceState)

        setContentView( R.layout.activity_splash_screen)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    fun getSavedTheme(): Int {
        val theme = getPreferences(Activity.MODE_PRIVATE).getString("theme", AppThemes.INDIGO)
        return when (theme) {
            AppThemes.INDIGO -> R.style.AppTheme_NoActionBar_Light
            AppThemes.PINK -> R.style.AppTheme_NoActionBar_Dark
            else -> R.style.AppTheme_NoActionBar_Light
        }
    }
}
