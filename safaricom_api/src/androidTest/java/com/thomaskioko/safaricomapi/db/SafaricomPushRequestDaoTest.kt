package com.thomaskioko.safaricomapi.db

import android.support.test.runner.AndroidJUnit4
import com.thomaskioko.safaricomapi.util.LiveDataTestUtil.getValue
import com.thomaskioko.safaricomapi.util.TestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SafaricomPushRequestDaoTest : SafaricomDbTest() {

    @Test
    fun insertLoadPushRequest() {

        val safaricomRequest = TestUtil.createPushRequest()
        db.safaricomPushRequestDao().insert(safaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(safaricomRequest.merchantRequestID))
        assertThat(loadedRequest.checkoutRequestID, `is`("ws_CO_DMZ_37595383_07062018174946163"))
    }

    @Test
    fun updateAndLoadRequest() {

        //Create a new object
        val safaricomRequest = TestUtil.createPushRequest("ws_CO_DMZ_37595383_07062018174946163")
        db.safaricomPushRequestDao().insert(safaricomRequest)

        //Create an updated record
        val updatedSafaricomRequest = TestUtil.createPushRequest("ws_CO_DMZ_37595383_07062018174946164")
        db.safaricomPushRequestDao().insert(updatedSafaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(updatedSafaricomRequest.merchantRequestID))

        //Check that the loaded request is the same as as the updated object
        assertThat(loadedRequest.checkoutRequestID, `is`("ws_CO_DMZ_37595383_07062018174946164"))

    }

}