package com.thomaskioko.daraja.repository

import android.arch.lifecycle.LiveData
import com.thomaskioko.daraja.model.SafaricomPushRequest
import com.thomaskioko.daraja.api.interceptor.SafaricomAuthInterceptor
import com.thomaskioko.daraja.api.service.SafaricomService
import com.thomaskioko.daraja.api.service.SafaricomTokenService
import com.thomaskioko.daraja.api.util.ApiResponse
import com.thomaskioko.daraja.api.util.AppExecutors
import com.thomaskioko.daraja.api.util.NetworkBoundResource
import com.thomaskioko.daraja.api.util.Resource
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.db.entity.SafaricomToken
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafaricomRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val safaricomService: SafaricomService,
        private val safaricomTokenService: SafaricomTokenService,
        private val safaricomTokenDao: SafaricomTokenDao,
        private val safaricomPushRequestDao: SafaricomPushRequestDao,
        private val safaricomAuthInterceptor: SafaricomAuthInterceptor
) {


    fun loadAccessToken(): LiveData<Resource<SafaricomToken>> {
        return object : NetworkBoundResource<SafaricomToken, SafaricomToken>(appExecutors) {
            override fun saveCallResult(item: SafaricomToken) {

                safaricomTokenDao.updateSafaricomToken(item)

                safaricomAuthInterceptor.setAuthToken(item.accessToken)
            }

            override fun shouldFetch(data: SafaricomToken?): Boolean {
                return data == null || data.expireTime.isBefore(OffsetDateTime.now(ZoneId.of("Z")))
            }

            override fun loadFromDb() = safaricomTokenDao.getAccessToken()

            override fun createCall(): LiveData<ApiResponse<SafaricomToken>> {
                return safaricomTokenService.getAccessToken()
            }
        }.asLiveData()
    }

    fun sendPaymentRequest(safaricomPushRequest: SafaricomPushRequest): LiveData<Resource<PushRequestResponse>> {
        return object : NetworkBoundResource<PushRequestResponse, PushRequestResponse>(appExecutors) {
            override fun saveCallResult(item: PushRequestResponse) {
                safaricomPushRequestDao.insert(item)
            }

            override fun shouldFetch(data: PushRequestResponse?): Boolean {
                //Always fetch data
                return true
            }

            override fun loadFromDb() = safaricomPushRequestDao.findById(safaricomPushRequest.accountReference)

            override fun createCall(): LiveData<ApiResponse<PushRequestResponse>> {
                return safaricomService.sendPushRequest(safaricomPushRequest)
            }
        }.asLiveData()
    }
}
