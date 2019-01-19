package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.MpesaPushRequestMapper
import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushRequestMapperTest {

    private val mapper = MpesaPushRequestMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = DataFactory.makeMpesaPushRequestEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = DataFactory.makeMpesaPushRequest()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: MpesaPushRequestEntity, model: MpesaPushRequest) {
        TestCase.assertEquals(entity.transaction, model.transaction)
        TestCase.assertEquals(entity.customer, model.customer)
    }
}