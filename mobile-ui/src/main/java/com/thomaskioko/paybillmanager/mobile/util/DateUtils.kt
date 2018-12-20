package com.thomaskioko.paybillmanager.mobile.util

import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

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
}
