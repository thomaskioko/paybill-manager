package com.thomaskioko.paybillmanager.data.test.store.jengatoken

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaRemote
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenRemoteDataStore
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class JengaRemoteDataStoreTest {

    private val remote = mock<JengaRemote>()
    private val store = JengaTokenRemoteDataStore(remote)

    @Test
    fun getSafaricomTokenCompletes() {
        stubRemoteGetSafaricomToken(Flowable.just(DataFactory.makeJengaTokenEntity()))

        val testObserver = store.getJengaToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSafaricomTokenReturnsData() {
        val data = DataFactory.makeJengaTokenEntity()
        stubRemoteGetSafaricomToken(Flowable.just(data))

        val testObserver = store.getJengaToken().test()

        //Check that the date returned is the data passed
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveSafaricomTokenThrowsException() {
        store.saveJengaToken(DataFactory.makeJengaTokenEntity()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearSafaricomTokenThrowsException() {
        store.clearJengaToken().test()
    }


    private fun stubRemoteGetSafaricomToken(observable: Flowable<JengaTokenEntity>) {
        whenever(remote.getJengaToken()).thenReturn(observable)
    }
}