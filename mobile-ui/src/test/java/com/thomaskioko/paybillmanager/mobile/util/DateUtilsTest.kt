package com.thomaskioko.paybillmanager.mobile.util

import com.thomaskioko.paybillmanager.mobile.util.DateUtils.dateToTimeStamp
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.formatTimeStampToDate
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

    @Test
    fun stringDateIsFormattedToTimeStamp() {

        //Given a string Date
        val timeStamp = dateToTimeStamp("12-23-2018 19:52:00")

        assertEquals(1545583920000, timeStamp)
    }

    @Test
    fun timStampFormatsDateToString(){
        //Given a timestamp
        val formattedDate = formatTimeStampToDate(1545583939000)

        //Verify that the formatted date is the correct format
        assertEquals("23rd December", formattedDate)

    }
}