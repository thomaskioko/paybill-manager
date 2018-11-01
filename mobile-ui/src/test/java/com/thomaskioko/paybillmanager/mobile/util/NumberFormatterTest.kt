package com.thomaskioko.paybillmanager.mobile.util

import com.thomaskioko.paybillmanager.mobile.ui.util.NumberFormatter.formatNumber
import org.junit.Test
import kotlin.test.assertEquals

class NumberFormatterTest {

    @Test
    fun numberIsFormattedCorrectly(){

        assertEquals(formatNumber("3400"), "3,400")
    }
}