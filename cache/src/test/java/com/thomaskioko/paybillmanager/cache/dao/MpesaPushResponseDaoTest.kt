package com.thomaskioko.paybillmanager.cache.dao

import com.thomaskioko.paybillmanager.cache.factory.MpesaResponseCachedFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MpesaPushResponseDaoTest : BaseDb() {

    @Test
    fun insertMpesaPushResponseSavesData() {
        val pushResponse = MpesaResponseCachedFactory.makeCacehedMpesaPushResponse()
        database.mpesaPushResponseDao().insertMpesaPushResponse(pushResponse)

        val testObserver = database.mpesaPushResponseDao().getMpesaPushResponses().test()
        testObserver.assertValue(listOf(pushResponse))
    }

    @Test
    fun getTokenSavesData() {
        val pushResponse = MpesaResponseCachedFactory.makeCacehedMpesaPushResponse()
        database.mpesaPushResponseDao().insertMpesaPushResponse(pushResponse)

        val testObserver = database.mpesaPushResponseDao().getMpesaPushResponses().test()
        testObserver
                .assertNoErrors()
                .assertValue(listOf(pushResponse))
    }
}