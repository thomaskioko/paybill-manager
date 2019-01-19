package com.thomaskioko.paybillmanager.mobile.util

import androidx.test.InstrumentationRegistry
import androidx.test.filters.SmallTest
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test


@SmallTest
class FileUtilsTest {

    private lateinit var fileUtils: FileUtils

    @Before
    fun setUp(){
        val context = InstrumentationRegistry.getTargetContext()
        fileUtils = FileUtils(context)
    }


    @Test
    fun fileUtilClassIsInitialised() {
        assertNotNull(fileUtils)
    }

    @Test
    fun fileUtilClassLoadsFile() {
        val signature = fileUtils.loadFile()
        assertNotNull(signature)
        assertEquals(true, signature!!.contains("-----END PRIVATE KEY-----"))
    }
}