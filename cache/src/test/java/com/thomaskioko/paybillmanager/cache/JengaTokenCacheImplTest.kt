package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedJengaTokenMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class JengaTokenCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private val entityMapper = CachedJengaTokenMapper()
    private val cache = JengaTokenCacheImpl(database, entityMapper)

    @Test
    fun clearTokenCompletes() {
        val testObserver = cache.clearJengaToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveTokenCompletes() {
        val token = TokenCachedFactory.makeJengaTokenEntity()

        val testObserver = cache.saveJengaToken(token).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val token = TokenCachedFactory.makeJengaTokenEntity()
        cache.saveJengaToken(token).test()

        val testObserver = cache.getJengaToken().test()
        testObserver.assertValue(token)
    }

    @Test
    fun isTokenCacheReturnsData() {
        val token = TokenCachedFactory.makeJengaTokenEntity()
        cache.saveJengaToken(token).test()

        val testObserver = cache.isTokenCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setExpiryTimeCompletes() {
        val testObserver = cache.setExpireTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun hasTokenExpiredReturnsNotExpired() {
        cache.setExpireTime(System.currentTimeMillis()).test()
        val testObserver = cache.hasTokenExpired().test()
        testObserver.assertValue(false)
    }

}