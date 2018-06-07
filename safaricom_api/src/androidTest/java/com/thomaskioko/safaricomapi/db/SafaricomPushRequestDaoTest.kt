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

        val safaricomRequest = TestUtil.createSafaricomPushRequest(12, "zuku-request")
        db.safaricomPushRequestDao().insert(safaricomRequest)

        val updatedSafaricomRequest = TestUtil.createSafaricomPushRequest(12, "zuku-request-june")
        db.safaricomPushRequestDao().insert(updatedSafaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(safaricomRequest.id))
        assertThat(loadedRequest.accountReference, `is`(updatedSafaricomRequest.accountReference))

    }

}