package com.thomaskioko.paybillmanager.mobile.util

import org.junit.Test
import kotlin.test.assertEquals

class StringUtilTest {


    @Test
    fun firstLetterIsCapitalised() {
        //When given as string
        val string = StringUtils.upperCaseFirstLetter("december")

        //Verify that the first letter is capital
        assertEquals(string.substring(0, 1), "D")
    }
}