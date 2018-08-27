package com.thomaskioko.paybillmanager.remote.mapper

import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
open class TokenModelMapperTest {

    private val mapper = TokenResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        //Create model instance
        val model = TestDataFactory.makeSafaricomTokenModel()

        //Create entity mapped instance
        val entity = mapper.mapFromModel(model)

        //verify that data is mapped correctly
        assertEquals(model.expiresIn.toLong(), entity.expiresIn)
        assertEquals(model.accessToken, entity.accessToken)

    }

}