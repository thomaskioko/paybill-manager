package com.thomaskioko.paybillmanager.util

import android.content.SharedPreferences
import com.thomaskioko.paybillmanager.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class SharedPrefsUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val HAS_SEEN_INTRO_SCREEN_KEY = "HAS_SEEN_INTRO_SCREEN"
    }


    fun setHasSeenIntroScreen(hasSeenIntro: Boolean) {
        sharedPreferences.edit().putBoolean(HAS_SEEN_INTRO_SCREEN_KEY, hasSeenIntro).apply()
    }

    fun getHasSeenIntroScreen(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_INTRO_SCREEN_KEY, false)
    }
}