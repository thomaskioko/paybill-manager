package com.thomaskioko.paybillmanager.mobile.util

object StringUtils {

    fun upperCaseFirstLetter(value: String): String {

        val array = value.toCharArray()
        array[0] = Character.toUpperCase(array[0])

        for (i in 1 until array.size) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toLowerCase(array[i])
            }
        }
        return String(array)
    }
}