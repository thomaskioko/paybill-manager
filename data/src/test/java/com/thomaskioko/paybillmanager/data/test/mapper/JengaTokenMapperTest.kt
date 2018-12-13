package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.data.mapper.TokenMapper
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.test.factory.TokenDataFactory
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class JengaTokenMapperTest {

    private val mapper = JengaTokenMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = TokenDataFactory.makeJengaTokenEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = TokenDataFactory.makeJengaToken()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: JengaTokenEntity, model: JengaToken) {
        assertEquals(entity.accessToken, model.accessToken)
        assertEquals(entity.expiresIn, model.expiresIn)
        assertEquals(entity.issuedAt, model.issuedAt)
        assertEquals(entity.tokenType, model.tokenType)
    }
}