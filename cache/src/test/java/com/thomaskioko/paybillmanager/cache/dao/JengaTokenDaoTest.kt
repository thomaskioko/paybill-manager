package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class JengaTokenDaoTest {

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
        val token = TokenCachedFactory.makeCachedJengaToken()
        database.jengaTokenDao().insertCachedToken(token)

        val testObserver = database.jengaTokenDao().getToken().test()

        //Verify that we get data
        testObserver.assertValue(token)
    }


    @Test
    fun deleteTokenClearsData() {
        val token = TokenCachedFactory.makeCachedJengaToken()
        //Invoke insert function to save data
        database.jengaTokenDao().insertCachedToken(token)
        //invoke delete function
        database.jengaTokenDao().deleteCachedToken()

        val testObserver = database.jengaTokenDao().getToken().test()

        //Verify that there is no data when we query
        testObserver.assertEmpty()
    }
}