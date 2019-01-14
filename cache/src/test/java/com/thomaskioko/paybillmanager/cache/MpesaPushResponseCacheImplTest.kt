package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.MpesaResponseCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedMpesaPushResponseMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class MpesaPushResponseCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val mapper = CachedMpesaPushResponseMapper()
    private val cacheImpl = MpesaPushResponseCacheImpl(database, mapper)


    @Test
    fun clearMpesaPushRequestsCompletes() {
        val testObserver = cacheImpl.clearMpesaPushRequests().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMpesaPushResponseCompletes() {
        val entity = MpesaResponseCachedFactory.makeMpesaPushResponseEntity()

        val testObserver = cacheImpl.saveMpesaPushResponse(entity).test()
        testObserver.assertComplete()
    }

    @Test
    fun getMpesaPushResponseReturnsData() {
        val entity = MpesaResponseCachedFactory.makeMpesaPushResponseEntity()
        cacheImpl.saveMpesaPushResponse(entity).test()

        val testObserver = cacheImpl.getMpesaStkPushRequests().test()
        testObserver.assertValue(listOf(entity))
    }

    @Test
    fun isMpesaPushResponseReturnsData() {
        val entity = MpesaResponseCachedFactory.makeMpesaPushResponseEntity()
        cacheImpl.saveMpesaPushResponse(entity).test()

        val testObserver = cacheImpl.isStkResponseCached(entity.transactionRef).test()
        testObserver.assertValue(true)
    }

}