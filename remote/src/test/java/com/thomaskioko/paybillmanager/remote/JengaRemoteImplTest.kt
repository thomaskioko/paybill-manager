package com.thomaskioko.paybillmanager.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import com.thomaskioko.paybillmanager.remote.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.remote.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.remote.model.JengaToken
import com.thomaskioko.paybillmanager.remote.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.remote.service.JengaService
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString


@RunWith(JUnit4::class)
class JengaRemoteImplTest {

    private val mapper = mock<JengaTokenMapper>()
    private val service = mock<JengaService>()
    private val responseMapper = mock<MpesaPushResponseMapper>()
    private val remote = JengaRemoteImpl(mapper, responseMapper, service)

    @Test
    fun getAccessTokenCompletes() {
        stubGetJengaAccessToken(Flowable.just(TestDataFactory.makeJengaToken()))
        stubTokenResponseModelMapperMapFromModel(any(), TestDataFactory.makeJengaTokenEntity())

        //Create a test observer instance & subscribe it getProjects
        val testObserver = remote.getJengaToken().test()

        //Verify that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun getMpesaPushResponseCompletes() {
        stubGetMpesaStkPush(Flowable.just(TestDataFactory.makeMpesaPushResponse()))
        stubMpesaPushResponseMapper(any(), TestDataFactory.makeMpesaPushResponseEntity())

        //Create a test observer instance & subscribe it getProjects
        val testObserver = remote.getMpesaPushResponse(anyString(), anyString()).test()

        //Verify that the observer completes
        testObserver.assertComplete()
    }


    private fun stubGetJengaAccessToken(observable: Flowable<JengaToken>) {
        //Mock the response of the service
        whenever(service.getAccessToken(any(), any(), any()))
                .thenReturn(observable)
    }

    private fun stubGetMpesaStkPush(observable: Flowable<MpesaPushResponse>) {
        //Mock the response of the service
        whenever(service.getMpesaStkPush(any(), any()))
                .thenReturn(observable)
    }

    private fun stubTokenResponseModelMapperMapFromModel(model: JengaToken, entity: JengaTokenEntity) {
        //Mock the mapper instance
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }

    private fun stubMpesaPushResponseMapper(model: MpesaPushResponse, entity: MpesaPushResponseEntity) {
        //Mock the mapper instance
        whenever(responseMapper.mapFromModel(model)).thenReturn(entity)
    }
}