package com.thomaskioko.paybillmanager.mobile.util

import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


    fun getDaysOfWeek(): MutableList<OffsetDateTime> {

        val instance = Instant.now()
        val offsetTime = OffsetDateTime.ofInstant(instance, ZoneId.of("GMT+03:00"))


        val days: MutableList<OffsetDateTime> = mutableListOf()
        days.add(offsetTime)

        for (i in 1..6) {
            days.add(offsetTime.plusDays(i.toLong()))
        }

        return days

    }


    fun formatTimeStampToDate(timeStamp: Long): String {

        val instance = Instant.EPOCH.plusMillis(timeStamp)
        val offsetTime = OffsetDateTime.ofInstant(instance, ZoneId.of("GMT+03:00"))

        val day = offsetTime.dayOfMonth
        val month = StringUtils.upperCaseFirstLetter(offsetTime.month.name.toLowerCase())

        if (day !in 11..18)
            return when (day % 10) {
                1 -> "${day}st $month"
                2 -> "${day}nd $month"
                3 -> "${day}rd $month"
                else -> "${day}th $month"
            }
        return "${day}th $month"

    }

    fun dateToTimeStamp(formattedDate: String): Long {

        val dateFormat = SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT+03:00")

        val parsedDate = dateFormat.parse(formattedDate)
        return parsedDate.time
    }
}
