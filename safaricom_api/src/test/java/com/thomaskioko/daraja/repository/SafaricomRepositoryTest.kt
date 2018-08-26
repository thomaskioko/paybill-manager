package com.thomaskioko.daraja.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.thomaskioko.daraja.api.interceptor.SafaricomAuthInterceptor
import com.thomaskioko.daraja.api.service.SafaricomRequestService
import com.thomaskioko.daraja.api.service.SafaricomTokenService
import com.thomaskioko.daraja.api.util.Resource
import com.thomaskioko.daraja.db.SafaricomDb
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.db.entity.SafaricomToken
import com.thomaskioko.daraja.util.ApiUtil.successCall
import com.thomaskioko.daraja.util.InstantAppExecutors
import com.thomaskioko.daraja.util.TestUtil
import com.thomaskioko.daraja.util.mock
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

    private val safaricomService = mock(SafaricomRequestService::class.java)
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
    fun loadAccessToken_ShouldFetchFromNetwork_WhenDataIsNull() {
        val dbData = MutableLiveData<SafaricomToken>()
        `when`(tokenDao.loadAccessToken()).thenReturn(dbData)

        val tokenResult = TestUtil.createToken()
        val call = successCall(tokenResult)
        `when`(tokenService.getAccessToken()).thenReturn(call)

        val data = repository.loadAccessToken()
        verify(tokenDao).loadAccessToken()
        verifyNoMoreInteractions(safaricomService)

        val observer = mock<Observer<Resource<SafaricomToken>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(tokenService)
        verify(observer).onChanged(Resource.loading(null))

        val updatedDbData = MutableLiveData<SafaricomToken>()
        `when`(tokenDao.loadAccessToken()).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(tokenService).getAccessToken()
        verify(tokenDao).update(tokenResult)

        updatedDbData.postValue(tokenResult)
        verify(observer).onChanged(Resource.success(tokenResult))
    }

    @Test
    fun loadAccessToken_ShouldFetchFromDB_WhenDataIsNotNullOrTimeIsNotExpired() {

        //Given that data is create and result is set
        val dbData = MutableLiveData<SafaricomToken>()
        val tokenResult = TestUtil.createToken()

        dbData.value = tokenResult
        `when`(tokenDao.loadAccessToken()).thenReturn(dbData)

        //Create an observer
        val observer = mock<Observer<Resource<SafaricomToken>>>()
        repository.loadAccessToken().observeForever(observer)

        //Verify that the service is not called
        verify(tokenService, never()).getAccessToken()

        //verify that data is successfully loaded
        verify(observer).onChanged(Resource.success(tokenResult))
    }

    @Test
    fun sendPaymentRequest_ShouldFetchFromNetwork_WhenDataIsNull() {

        val safPushRequest = TestUtil.createSafaricomPushRequest()
        val pushRequest = TestUtil.createPushRequest()

        val dbData = MutableLiveData<PushRequestResponse>()
        `when`(pushRequestDao.findById(pushRequest.checkoutRequestID)).thenReturn(dbData)


        val call = successCall(pushRequest)
        `when`(safaricomService.sendPushRequest(safPushRequest)).thenReturn(call)

        val pushRequestResponse = repository.sendPaymentRequest(pushRequest.checkoutRequestID,
                safPushRequest
        )
        verify(pushRequestDao).findById(pushRequest.checkoutRequestID)
        verifyNoMoreInteractions(safaricomService)

        val observer = mock<Observer<Resource<PushRequestResponse>>>()
        pushRequestResponse.observeForever(observer)
        verifyNoMoreInteractions(safaricomService)
        verify(observer).onChanged(Resource.loading(null))

        val updatedDbData = MutableLiveData<PushRequestResponse>()
        `when`(pushRequestDao.findById(pushRequest.checkoutRequestID)).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(safaricomService).sendPushRequest(safPushRequest)
        verify(pushRequestDao).insert(pushRequest)

        updatedDbData.postValue(pushRequest)
        verify(observer).onChanged(Resource.success(pushRequest))
    }


}