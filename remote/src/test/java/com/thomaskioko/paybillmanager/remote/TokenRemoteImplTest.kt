package com.thomaskioko.paybillmanager.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.remote.factory.TestDataFactory
import com.thomaskioko.paybillmanager.remote.mapper.TokenResponseModelMapper
import com.thomaskioko.paybillmanager.remote.model.SafaricomTokenModel
import com.thomaskioko.paybillmanager.remote.service.TokenService
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TokenRemoteImplTest {

    private val mapper = mock<TokenResponseModelMapper>()
    private val service = mock<TokenService>()
    private val remote = TokenRemoteImpl(mapper, service)

    @Test
    fun getTokenCompletes() {
        stubTokenServiceSearchRepositories(Flowable.just(TestDataFactory.makeSafaricomTokenModel()))
        stubTokenResponseModelMapperMapFromModel(any(), TestDataFactory.makeSafaricomEntity())

        //Create a test observer instance & subscribe it getProjects
        val testObserver = remote.getSafaricomToken().test()

        //Verify that the observer completes
        testObserver.assertComplete()
    }


    private fun stubTokenServiceSearchRepositories(observable:
                                                  Flowable<SafaricomTokenModel>) {
        //Mock the response of the service
        whenever(service.getAccessToken())
                .thenReturn(observable)
    }

    private fun stubTokenResponseModelMapperMapFromModel(model: SafaricomTokenModel,
                                                         entity: SafaricomTokenEntity) {
        //Mock the mapper instance
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }
}