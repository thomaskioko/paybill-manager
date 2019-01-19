package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushResponseMapperTest {

    private val mapper = MpesaPushResponseMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = DataFactory.makeMpesaPushResponseEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = DataFactory.makeMpesaPushResponse()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: MpesaPushResponseEntity, model: MpesaPushResponse) {
        TestCase.assertEquals(entity.transactionReference, model.transactionReference)
        TestCase.assertEquals(entity.statusMessage, model.statusMessage)
    }
}