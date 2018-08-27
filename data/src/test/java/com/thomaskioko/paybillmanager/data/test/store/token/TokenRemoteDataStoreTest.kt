package com.thomaskioko.paybillmanager.data.test.store.token

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.data.store.token.TokenRemoteDataStore
import com.thomaskioko.paybillmanager.data.test.factory.TokenDataFactory
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TokenRemoteDataStoreTest {

    private val remote = mock<TokenRemote>()
    private val store = TokenRemoteDataStore(remote)

    @Test
    fun getSafaricomTokenCompletes() {
        stubRemoteGetSafaricomToken(Flowable.just(TokenDataFactory.makeSafaricomTokenEntity()))

        val testObserver = store.getSafaricomToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSafaricomTokenReturnsData() {
        val data = TokenDataFactory.makeSafaricomTokenEntity()
        stubRemoteGetSafaricomToken(Flowable.just(data))

        val testObserver = store.getSafaricomToken().test()

        //Check that the date returned is the data passed
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveSafaricomTokenThrowsException() {
        store.saveSafaricomToken(TokenDataFactory.makeSafaricomTokenEntity()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearSafaricomTokenThrowsException() {
        store.clearSafaricomToken().test()
    }


    private fun stubRemoteGetSafaricomToken(observable: Flowable<SafaricomTokenEntity>) {
        whenever(remote.getSafaricomToken()).thenReturn(observable)
    }
}