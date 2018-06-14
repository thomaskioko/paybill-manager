package com.thomaskioko.daraja.db.dao

import androidx.test.runner.AndroidJUnit4
import com.thomaskioko.daraja.db.SafaricomDbTest
import com.thomaskioko.daraja.util.LiveDataTestUtil.getValue
import com.thomaskioko.daraja.util.TestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SafaricomPushRequestDaoTest : SafaricomDbTest() {

    @Test
    fun insert_PushRequestDao_ShouldLoadInsertedRecord() {

        val safaricomRequest = TestUtil.createPushRequest()
        db.safaricomPushRequestDao().insert(safaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(safaricomRequest.checkoutRequestID))
        assertThat(loadedRequest.checkoutRequestID, `is`("ws_CO_DMZ_37595383_07062018174946163"))
    }

    @Test
    fun findAll_PushRequestDao_ShouldReturnRecords() {
        //Create a new object

        db.safaricomPushRequestDao().insert(TestUtil.pushResponseList)

        //Given the db has records
        val result = getValue(db.safaricomPushRequestDao().findAll())

        //Check that the size it not 0
        assertThat(result.size, `is`(IsNot.not(0)))
    }

    @Test
    fun findRequestByMerchantId_PushRequestDao_ShouldReturnRecord() {

        //Create a new object
        val safaricomRequest = TestUtil.createPushRequest("ws_CO_DMZ_37595383_07062018174946163")
        db.safaricomPushRequestDao().insert(safaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(safaricomRequest.checkoutRequestID))

        assertThat(loadedRequest, `is`(IsNull.notNullValue()))
    }

    @Test
    fun updatePushRequest_PushRequestDao_ShouldLoadUpdatedRecord() {

        //Create a new object
        val safaricomRequest = TestUtil.createPushRequest("ws_CO_DMZ_37595383_07062018174946163")
        db.safaricomPushRequestDao().insert(safaricomRequest)

        //Create an updated record
        val updatedSafaricomRequest = TestUtil.createPushRequest("ws_CO_DMZ_37595383_07062018174946164")
        db.safaricomPushRequestDao().insert(updatedSafaricomRequest)

        val loadedRequest = getValue(db.safaricomPushRequestDao().findById(updatedSafaricomRequest.checkoutRequestID))

        //Check that the loaded request is the same as as the updated object
        assertThat(loadedRequest.checkoutRequestID, `is`("ws_CO_DMZ_37595383_07062018174946164"))

    }

    @Test
    fun deleteToken_PushRequestDao_ShouldNotReturnData() {

        val safaricomToken = TestUtil.createPushRequest()
        db.safaricomPushRequestDao().insert(safaricomToken)

        val loadedRecord = getValue(db.safaricomPushRequestDao().findById(safaricomToken.checkoutRequestID))

        //Verify that data from db is same as data created
        assertThat(loadedRecord.checkoutRequestID, `is`(safaricomToken.checkoutRequestID))

        db.safaricomPushRequestDao().deleteAll()

        val result = getValue(db.safaricomPushRequestDao().findAll())
        assertThat(result.size, `is`(0))

    }

}