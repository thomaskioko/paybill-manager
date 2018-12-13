package com.thomaskioko.paybillmanager.remote.mapper

import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
open class JengaTokenMapperTest {

    private val mapper = JengaTokenMapper()

    @Test
    fun mapFromModelMapsData() {
        //Create model instance
        val model = TestDataFactory.makeJengaToken()

        //Create entity mapped instance
        val entity = mapper.mapFromModel(model)

        //verify that data is mapped correctly
        assertEquals(model.issuedAt, entity.issuedAt)
        assertEquals(model.tokenType, entity.tokenType)
        assertEquals(model.expiresIn, entity.expiresIn)
        assertEquals(model.accessToken, entity.accessToken)

    }

}