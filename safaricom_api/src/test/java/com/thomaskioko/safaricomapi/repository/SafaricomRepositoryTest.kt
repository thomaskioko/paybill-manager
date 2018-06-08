package com.thomaskioko.safaricomapi.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.thomaskioko.daraja.repository.SafaricomRepository
import com.thomaskioko.daraja.repository.api.interceptor.SafaricomAuthInterceptor
import com.thomaskioko.daraja.repository.api.service.SafaricomService
import com.thomaskioko.daraja.repository.api.service.SafaricomTokenService
import com.thomaskioko.daraja.repository.api.util.Resource
import com.thomaskioko.daraja.repository.db.SafaricomDb
import com.thomaskioko.daraja.repository.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.repository.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken
import com.thomaskioko.safaricomapi.util.ApiUtil.successCall
import com.thomaskioko.safaricomapi.util.InstantAppExecutors
import com.thomaskioko.safaricomapi.util.TestUtil
import com.thomaskioko.safaricomapi.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class SafaricomRepositoryTest {


    private lateinit var repository: SafaricomRepository
    private lateinit var authInterceptor: SafaricomAuthInterceptor

    private val tokenDao = mock(SafaricomTokenDao::class.java)
    private val pushRequestDao = mock(SafaricomPushRequestDao::class.java)

    private val safaricomService = mock(SafaricomService::class.java)
    private val tokenService = mock(SafaricomTokenService::class.java)


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(SafaricomDb::class.java)
        `when`(db.safaricomTokenDao()).thenReturn(tokenDao)
        `when`(db.safaricomPushRequestDao()).thenReturn(pushRequestDao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()

        authInterceptor = SafaricomAuthInterceptor
        repository = SafaricomRepository(
                InstantAppExecutors(), safaricomService, tokenService, tokenDao, pushRequestDao, authInterceptor
        )
    }

    @Test
    fun fetchFromNetwork() {
        val dbData = MutableLiveData<SafaricomToken>()
        `when`(tokenDao.getAccessToken()).thenReturn(dbData)

        val tokenResult = TestUtil.createToken()
        val call = successCall(tokenResult)
        `when`(tokenService.getAccessToken()).thenReturn(call)

        val data = repository.loadAccessToken()
        verify(tokenDao).getAccessToken()
        verifyNoMoreInteractions(safaricomService)

        val observer = mock<Observer<Resource<SafaricomToken>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(tokenService)
        verify(observer).onChanged(Resource.loading(null))

        val updatedDbData = MutableLiveData<SafaricomToken>()
        `when`(tokenDao.getAccessToken()).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(tokenService).getAccessToken()
        verify(tokenDao).updateSafaricomToken(tokenResult)

        updatedDbData.postValue(tokenResult)
        verify(observer).onChanged(Resource.success(tokenResult))
    }

    @Test
    fun loadFromDb(){

        //Given that data is create and result is set
        val dbData = MutableLiveData<SafaricomToken>()
        val tokenResult = TestUtil.createToken()

        dbData.value = tokenResult
        `when`(tokenDao.getAccessToken()).thenReturn(dbData)

        //Create an observer
        val observer = mock<Observer<Resource<SafaricomToken>>>()
        repository.loadAccessToken().observeForever(observer)

        //Verify that the service is not called
        verify(tokenService, never()).getAccessToken()

        //verify that data is successfully loaded
        verify(observer).onChanged(Resource.success(tokenResult))
    }

}