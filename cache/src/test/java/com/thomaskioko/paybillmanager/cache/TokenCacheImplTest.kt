package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.TokenCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedTokenMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class TokenCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private val entityMapper = CachedTokenMapper()
    private val cache = TokenCacheImpl(database, entityMapper)

    @Test
    fun clearTokenCompletes() {
        val testObserver = cache.clearSafaricomToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveTokenCompletes() {
        val projects = TokenCachedFactory.makeSafaricomTokenEntity()

        val testObserver = cache.saveSafaricomToken(projects).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val projects = TokenCachedFactory.makeSafaricomTokenEntity()
        cache.saveSafaricomToken(projects).test()

        val testObserver = cache.getSafaricomToken().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun isTokenCacheReturnsData() {
        val projects = TokenCachedFactory.makeSafaricomTokenEntity()
        cache.saveSafaricomToken(projects).test()

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