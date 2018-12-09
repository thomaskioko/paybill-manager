package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedToken
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedTokenMapperTest {

    private val mapper = CachedTokenMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = TokenCachedFactory.makeCachedToken()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = TokenCachedFactory.makeSafaricomTokenEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedToken, entity: SafaricomTokenEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.expiresIn, entity.expiresIn)
        assertEquals(model.accessToken, entity.accessToken)
    }
}