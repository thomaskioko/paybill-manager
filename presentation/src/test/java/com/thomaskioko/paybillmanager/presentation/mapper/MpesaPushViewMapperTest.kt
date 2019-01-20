package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.presentation.factory.JengaFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MpesaPushViewMapperTest {

    private val mapper = MpesaPushViewMapper()

    @Test
    fun mapToViewMapsData() {
        val entity = JengaFactory.makeMpesaPushResponse()
        val view = mapper.mapToView(entity)

        assertEquals(view.transactionReference, entity.transactionReference)
        assertEquals(view.statusMessage, entity.statusMessage)
    }

}