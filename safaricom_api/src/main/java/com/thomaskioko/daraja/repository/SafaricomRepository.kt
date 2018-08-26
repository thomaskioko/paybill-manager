package com.thomaskioko.daraja.repository

import androidx.lifecycle.LiveData
import com.thomaskioko.daraja.api.interceptor.SafaricomAuthInterceptor
import com.thomaskioko.daraja.api.service.SafaricomRequestService
import com.thomaskioko.daraja.api.service.SafaricomTokenService
import com.thomaskioko.daraja.api.util.ApiResponse
import com.thomaskioko.daraja.api.util.AppExecutors
import com.thomaskioko.daraja.api.util.NetworkBoundResource
import com.thomaskioko.daraja.api.util.Resource
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.db.entity.SafaricomToken
import com.thomaskioko.daraja.model.SafaricomPushRequest
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafaricomRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val safaricomService: SafaricomRequestService,
        private val safaricomTokenService: SafaricomTokenService,
        private val safaricomTokenDao: SafaricomTokenDao,
        private val safaricomPushRequestDao: SafaricomPushRequestDao,
        private val safaricomAuthInterceptor: SafaricomAuthInterceptor
) {


    fun loadAccessToken(): LiveData<Resource<SafaricomToken>> {
        return object : NetworkBoundResource<SafaricomToken, SafaricomToken>(appExecutors) {
            override fun saveCallResult(item: SafaricomToken) {

                safaricomTokenDao.update(item)

                safaricomAuthInterceptor.setAuthToken(item.accessToken)
            }

            override fun shouldFetch(data: SafaricomToken?): Boolean {
                return data == null || data.expireTime.isBefore(OffsetDateTime.now(ZoneId.of("Z")))
            }

            override fun loadFromDb() = safaricomTokenDao.loadAccessToken()

            override fun createCall(): LiveData<ApiResponse<SafaricomToken>> {
                return safaricomTokenService.getAccessToken()
            }
        }.asLiveData()
    }

    fun sendPaymentRequest(checkoutRequestId: String, safaricomPushRequest: SafaricomPushRequest
    ): LiveData<Resource<PushRequestResponse>> {
        return object : NetworkBoundResource<PushRequestResponse, PushRequestResponse>(appExecutors) {
            override fun saveCallResult(item: PushRequestResponse) {
                safaricomPushRequestDao.insert(item)
            }

            override fun shouldFetch(data: PushRequestResponse?): Boolean {
                return data == null || data.checkoutRequestID != checkoutRequestId
            }

            override fun loadFromDb() = safaricomPushRequestDao.findById(checkoutRequestId)

            override fun createCall(): LiveData<ApiResponse<PushRequestResponse>> {
                return safaricomService.sendPushRequest(safaricomPushRequest)
            }
        }.asLiveData()
    }
}
