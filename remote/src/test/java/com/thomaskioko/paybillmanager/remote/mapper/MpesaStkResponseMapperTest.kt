package com.thomaskioko.paybillmanager.remote.mapper

import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
open class MpesaStkResponseMapperTest {

    private val mapper = MpesaPushResponseMapper()

    @Test
    fun mapFromModelMapsData() {
        //Create model instance
        val model = TestDataFactory.makeMpesaPushResponse()

        //Create entity mapped instance
        val entity = mapper.mapFromModel(model)

        //verify that data is mapped correctly
        assertEquals(model.transactionReference, entity.transactionReference)
        assertEquals(model.statusMessage, entity.statusMessage)

    }

}