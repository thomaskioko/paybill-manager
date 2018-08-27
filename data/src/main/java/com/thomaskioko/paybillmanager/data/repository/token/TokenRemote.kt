package com.thomaskioko.paybillmanager.data.repository.token

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import io.reactivex.Flowable

interface TokenRemote {

    fun getSafaricomToken(): Flowable<SafaricomTokenEntity>
}