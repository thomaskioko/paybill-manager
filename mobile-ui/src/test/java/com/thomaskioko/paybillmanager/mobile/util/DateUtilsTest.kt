package com.thomaskioko.paybillmanager.mobile.util

import com.thomaskioko.paybillmanager.mobile.util.DateUtils.dateToTimeStamp
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.formatTimeStampToDate
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.getMonthFromTimeStamp
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
        val formattedDateSt = formatTimeStampToDate(1538412720000)

        //Verify that the formatted date is the correct format with `th`
        assertEquals("1st October", formattedDateSt)

        //Given a timestamp
        val formattedDateTh = formatTimeStampToDate(1545674983)

        //Verify that the formatted date is the correct format with `th`
        assertEquals("19th January", formattedDateTh)

        //Given a timestamp
        val formattedDateNd = formatTimeStampToDate(1545497520000)

        //Verify that the formatted date is the correct format with `nd`
        assertEquals("22nd December", formattedDateNd)

        //Given a timestamp
        val formattedDateDec = formatTimeStampToDate(1545583939000)

        //Verify that the formatted date is the correct format
        assertEquals("23rd December", formattedDateDec)

    }

    @Test
    fun getMonthFromTimeStamp(){
        //Given a timestamp
        val formattedDate = getMonthFromTimeStamp(1545583939000)

        //Verify that the formatted date is the correct format
        assertEquals("23rd", formattedDate)

    }

    @Test
    fun getMonthFromTimeStampReturnsTheRightFormat(){

        //Given a timestamp
        val formattedDateSt = getMonthFromTimeStamp(1543683120000)

        //Verify that the formatted date is the correct format with `th`
        assertEquals("1st", formattedDateSt)

        //Given a timestamp
        val formattedDateTh = getMonthFromTimeStamp(1545674983)

        //Verify that the formatted date is the correct format with `th`
        assertEquals("19th", formattedDateTh)

        //Given a timestamp
        val formattedDateNd = getMonthFromTimeStamp(1545497520000)

        //Verify that the formatted date is the correct format with `nd`
        assertEquals("22nd", formattedDateNd)

        //Given a timestamp
        val formattedDate = getMonthFromTimeStamp(1545583939000)

        //Verify that the formatted date is the correct format with `rd`
        assertEquals("23rd", formattedDate)

    }
}