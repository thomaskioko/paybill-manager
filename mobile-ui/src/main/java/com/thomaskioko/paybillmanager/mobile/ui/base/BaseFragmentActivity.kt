package com.thomaskioko.paybillmanager.mobile.ui.base

import android.app.Activity
import android.os.Bundle
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.util.AppThemes.Companion.INDIGO
import com.thomaskioko.paybillmanager.mobile.util.AppThemes.Companion.PINK
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseFragmentActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    var isThemeDark = false


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(getSavedTheme())
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        isThemeDark = getSavedTheme() != R.style.AppTheme_NoActionBar_Light
    }

    abstract fun getLayoutId(): Int

    fun saveTheme(value: String) {
        val editor = getPreferences(Activity.MODE_PRIVATE).edit()
        editor.putString("theme", value)
        editor.apply()
        recreate()
    }

    fun getSavedTheme(): Int {
        val theme = getPreferences(Activity.MODE_PRIVATE).getString("theme", INDIGO)
        return when (theme) {
            INDIGO -> R.style.AppTheme_NoActionBar_Light
            PINK -> R.style.AppTheme_NoActionBar_Dark
            else -> R.style.AppTheme_NoActionBar_Light
        }
    }
}