package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedJengaToken
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedJengaTokenMapperTest {

    private val mapper = CachedJengaTokenMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = TokenCachedFactory.makeCachedJengaToken()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = TokenCachedFactory.makeJengaTokenEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedJengaToken, entity: JengaTokenEntity) {
        assertEquals(model.tokenType, entity.tokenType)
        assertEquals(model.issuedAt, entity.issuedAt)
        assertEquals(model.expiresIn, entity.expiresIn)
        assertEquals(model.accessToken, entity.accessToken)
    }
}