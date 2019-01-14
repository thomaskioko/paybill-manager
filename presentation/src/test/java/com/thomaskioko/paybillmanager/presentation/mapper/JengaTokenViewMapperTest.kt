package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.presentation.factory.JengaFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class JengaTokenViewMapperTest {

    private val mapper = JengaTokenViewMapper()

    @Test
    fun mapToViewMapsData() {
        val entity = JengaFactory.makeJengaToken()
        val tokenView = mapper.mapToView(entity)

        assertEquals(tokenView.tokenType, entity.tokenType)
        assertEquals(tokenView.issuedAt, entity.issuedAt)
        assertEquals(tokenView.expiresIn, entity.expiresIn)
        assertEquals(tokenView.accessToken, entity.accessToken)
    }

}