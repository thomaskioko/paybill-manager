package com.thomaskioko.safaricomapi.api

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.thomaskioko.daraja.repository.api.service.SafaricomTokenService
import com.thomaskioko.daraja.repository.api.util.ApiSuccessResponse
import com.thomaskioko.daraja.repository.api.util.livedata.LiveDataCallAdapterFactory
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken
import com.thomaskioko.safaricomapi.util.LiveDataTestUtil.getValue
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
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
class SafaricomTokenServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var tokenService: SafaricomTokenService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        tokenService = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(SafaricomTokenService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun generateAccessToken() {
        enqueueResponse("token-response.json")

        val result = (getValue(tokenService.getAccessToken()) as ApiSuccessResponse).body

        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/generate?grant_type=client_credentials"))

        assertThat<SafaricomToken>(result, IsNull.notNullValue())

        assertThat(result.accessToken, `is`("jK13flUwxNhIsYPzvNAJwslDnruQ"))
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