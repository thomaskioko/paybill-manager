package com.thomaskioko.safaricomapi.api

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.thomaskioko.daraja.repository.api.service.SafaricomService
import com.thomaskioko.daraja.repository.api.util.ApiSuccessResponse
import com.thomaskioko.daraja.repository.api.util.livedata.LiveDataCallAdapterFactory
import com.thomaskioko.daraja.repository.db.entity.PushRequestResponse
import com.thomaskioko.safaricomapi.util.LiveDataTestUtil.getValue
import com.thomaskioko.safaricomapi.util.TestUtil
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class SafaricomRequestServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: SafaricomService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(SafaricomService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun generateAccessToken() {
        enqueueResponse("request-response.json")

        val result = (getValue(service.sendPushRequest(TestUtil.createSafaricomPushRequest())) as ApiSuccessResponse).body

        val request = mockWebServer.takeRequest()
        assertThat(request.path, CoreMatchers.`is`("/mpesa/stkpush/v1/processrequest"))

        assertThat<PushRequestResponse>(result, IsNull.notNullValue())

        assertThat(result.merchantRequestID, `is`("25745-1565482-1"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(source.readString(Charsets.UTF_8))
        )
    }
}