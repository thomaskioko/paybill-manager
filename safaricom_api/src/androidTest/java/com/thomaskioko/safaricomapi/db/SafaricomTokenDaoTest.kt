package com.thomaskioko.safaricomapi.db

import android.support.test.runner.AndroidJUnit4
import com.thomaskioko.safaricomapi.util.LiveDataTestUtil.getValue
import com.thomaskioko.safaricomapi.util.TestUtil
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SafaricomTokenDaoTest : SafaricomDbTest() {


    @Test
    fun insertLoadToken() {

        val safaricomToken = TestUtil.createToken()
        db.safaricomTokenDao().insertSafaricomToken(safaricomToken)

        val loadedToken = getValue(db.safaricomTokenDao().getAccessToken())

        //Verify that data from db is same as data created
        assertThat(loadedToken.accessToken, `is`(safaricomToken.accessToken))
    }

    @Test
    fun updateAndLoad() {

        val safaricomToken = TestUtil.createToken()
        db.safaricomTokenDao().insertSafaricomToken(safaricomToken)

        val updatedSafaricomToken = TestUtil.createToken("vK13flUwxNhIsYPzvNAJwslDnruQE")
        db.safaricomTokenDao().updateSafaricomToken(updatedSafaricomToken)

        val loadedToken = getValue(db.safaricomTokenDao().getAccessToken())

        //Verify that data from db is same as data updated
        assertThat(loadedToken.accessToken, `is`(updatedSafaricomToken.accessToken))
    }

    @Test
    fun timDateIsNotNul(){

        //Given that data is created and inserted
        val safaricomToken = TestUtil.createToken()
        db.safaricomTokenDao().insertSafaricomToken(safaricomToken)

        //Check that offsetTime is not null
        assertThat(safaricomToken.expireTime, `is`(notNullValue()))
    }
}

