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
    fun insertLoadToken() {

        val safaricomRequest = TestUtil.createSafaricomPushRequest()
        db.safaricomPushRequestDao().insert(safaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(safaricomRequest.id))
        assertThat(loadedRequest.accountReference, `is`(safaricomRequest.accountReference))
    }

    @Test
    fun updateAndLoad() {

        //Create a new object
        val safaricomRequest = TestUtil.createSafaricomPushRequest(12, "zuku-request")
        db.safaricomPushRequestDao().insert(safaricomRequest)

        //Create an updated record
        val updatedSafaricomRequest = TestUtil.createSafaricomPushRequest(12, "zuku-request-june")
        db.safaricomPushRequestDao().insert(updatedSafaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(updatedSafaricomRequest.id))

        //Check that the loaded request is the same as as the updated object
        assertThat(loadedRequest.accountReference, `is`(updatedSafaricomRequest.accountReference))

    }

}