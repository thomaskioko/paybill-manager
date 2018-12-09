package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.TokenMapper
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.test.factory.TokenDataFactory
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TokenMapperTest {

    private val mapper = TokenMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = TokenDataFactory.makeSafaricomTokenEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = TokenDataFactory.makeSafaricomToken()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: SafaricomTokenEntity, model: SafaricomToken) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.accessToken, model.accessToken)
        assertEquals(entity.expiresIn, model.expiresIn)
    }
}