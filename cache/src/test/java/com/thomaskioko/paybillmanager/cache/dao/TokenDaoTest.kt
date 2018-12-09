package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.nhaarman.mockitokotlin2.any
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.factory.DataFactory
import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class TokenDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getTokenSavesData() {
        val token = TokenCachedFactory.makeCachedToken()
        database.tokenDao().insertCachedToken(token)

        val testObserver = database.tokenDao().getToken().test()
        testObserver.assertValue(token)
    }



    @Test
    fun deleteBillsClearsData() {
        val token = TokenCachedFactory.makeCachedToken()
        database.tokenDao().insertCachedToken(token)
        database.tokenDao().deleteCachedToken()

        val testObserver = database.tokenDao().getToken().test()
        testObserver.assertEmpty()
    }
}