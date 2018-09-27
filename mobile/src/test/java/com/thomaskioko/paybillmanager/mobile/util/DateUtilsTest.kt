package com.thomaskioko.paybillmanager.mobile.util

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DateUtilsTest {


    @Test
    fun getDaysOfWeekNotNull() {
        val date = DateUtils.getDaysOfWeek()
        assertNotNull(date)
    }

    @Test
    fun getDaysOfWeekReturnsData() {
        val date = DateUtils.getDaysOfWeek()

        assertEquals(7, date.size)
        assertNotNull(date[0])
    }
}