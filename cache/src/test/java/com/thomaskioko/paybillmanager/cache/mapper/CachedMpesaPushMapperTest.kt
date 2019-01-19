package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.MpesaResponseCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedMpesaPushResponse
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedMpesaPushMapperTest {

    private val mapper = CachedMpesaPushResponseMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = MpesaResponseCachedFactory.makeCacehedMpesaPushResponse()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = MpesaResponseCachedFactory.makeMpesaPushResponseEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedMpesaPushResponse, entity: MpesaPushResponseEntity) {
        assertEquals(model.transactionReference, entity.transactionReference)
        assertEquals(model.statusMessage, entity.statusMessage)
    }
}