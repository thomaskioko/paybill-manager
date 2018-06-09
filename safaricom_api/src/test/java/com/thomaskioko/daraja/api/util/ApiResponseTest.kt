package com.thomaskioko.daraja.api.util

import com.thomaskioko.daraja.api.util.ApiErrorResponse
import com.thomaskioko.daraja.api.util.ApiResponse
import com.thomaskioko.daraja.api.util.ApiSuccessResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {
    @Test
    fun exception() {
        val exception = Exception("foo")
        val (errorMessage) = ApiResponse.create<String>(exception)
        assertThat<String>(errorMessage, `is`("foo"))
    }

    @Test
    fun success() {
        val apiResponse: ApiSuccessResponse<String> = ApiResponse
                .create<String>(Response.success("foo")) as ApiSuccessResponse<String>
        assertThat<String>(apiResponse.body, `is`("foo"))
        assertThat<Int>(apiResponse.nextPage, `is`(nullValue()))
    }


    @Test
    fun error() {
        val errorResponse = Response.error<String>(
                400,
                ResponseBody.create(MediaType.parse("application/txt"), "blah")
        )
        val (errorMessage) = ApiResponse.create<String>(errorResponse) as ApiErrorResponse<String>
        assertThat<String>(errorMessage, `is`("blah"))
    }
}