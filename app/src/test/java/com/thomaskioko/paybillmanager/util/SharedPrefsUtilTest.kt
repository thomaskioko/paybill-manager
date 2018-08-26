package com.thomaskioko.paybillmanager.util

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@RunWith(JUnit4::class)
class SharedPrefsUtilTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val sharedPreferences = mock(SharedPreferences::class.java)
    private val mockContext = mock(Context::class.java)
    private lateinit var sharedPrefsUtil: SharedPrefsUtil


    @Before
    fun setUp() {
        `when`(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)

        sharedPrefsUtil = SharedPrefsUtil(sharedPreferences)

    }


    @Test
    fun sharedPrefsUtil_DefaultHasSeenIntroScreen_ReturnsFalse() {
        //Validate that the default value is false.
        assertThat(sharedPrefsUtil.getHasSeenIntroScreen(), `is`(false))
    }

    @Test
    fun sharedPrefsUtil_SetHasSeenIntroScreen_ReturnsTrue() {

        //Given that the shared preference value is set to true
        `when`(sharedPreferences.getBoolean("HAS_SEEN_INTRO_SCREEN", false)).thenReturn(true)

        // Validate that the value retrieved is true
        assertThat(sharedPrefsUtil.getHasSeenIntroScreen(), `is`(true))
    }

}
