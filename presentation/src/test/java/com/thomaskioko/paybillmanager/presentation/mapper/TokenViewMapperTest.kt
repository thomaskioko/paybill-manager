package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.presentation.factory.TokenFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TokenViewMapperTest {

    private val mapper = TokenViewMapper()

    @Test
    fun mapToViewMapsData() {
        val entity = TokenFactory.makeSafaricomToken()
        val tokenView = mapper.mapToView(entity)

        assertEquals(tokenView.id, entity.id)
        assertEquals(tokenView.expiresIn, entity.expiresIn)
        assertEquals(tokenView.accessToken, entity.accessToken)
    }

}