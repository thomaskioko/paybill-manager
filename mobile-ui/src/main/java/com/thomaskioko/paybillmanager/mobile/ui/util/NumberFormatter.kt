package com.thomaskioko.paybillmanager.mobile.ui.util

import java.text.NumberFormat
import java.util.*

object NumberFormatter {

    fun formatNumber(number: String): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)

        return numberFormat.format(number.toInt())
    }
}