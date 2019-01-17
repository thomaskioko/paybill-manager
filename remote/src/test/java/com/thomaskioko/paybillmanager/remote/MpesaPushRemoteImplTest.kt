package com.thomaskioko.paybillmanager.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory.makeMpesaPushResponse
import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory.makeMpesaPushResponseEntity
import com.thomaskioko.paybillmanager.remote.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.remote.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.remote.service.JengaService
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MpesaPushRemoteImplTest {

    private val mapper = mock<MpesaPushResponseMapper>()
    private val service = mock<JengaService>()
    private val remote = MpesaRemoteImpl(mapper, service)

    @Test
    fun getMpesaPushResponseCompletes() {
        stubGetMpesaStkPush(Flowable.just(TestDataFactory.makeMpesaPushResponse()))
        stubMpesaPushResponseMapper(makeMpesaPushResponse(), makeMpesaPushResponseEntity())

        //Create a test observer instance & subscribe it getProjects
        val testObserver = remote.getMpesaStkPushRequest(
                "Bearer: ", "signaturePayload", TestDataFactory.makeMpesaPushRequest()
        ).test()

        //Verify that the observer completes
        testObserver.assertComplete()
    }

    private fun stubGetMpesaStkPush(observable: Flowable<MpesaPushResponse>) {
        //Mock the response of the service
        whenever(service.getMpesaStkPush(any(), any(), any()))
                .thenReturn(observable)
    }


    private fun stubMpesaPushResponseMapper(model: MpesaPushResponse, entity: MpesaPushResponseEntity) {
        //Mock the mapper instance
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }
}