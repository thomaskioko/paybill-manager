package com.thomaskioko.paybillmanager.data.store.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaTokenCacheDataStore @Inject constructor(
        private val tokenCache: JengaTokenCache
) : JengaTokenDataStore {
    override fun clearJengaToken(): Completable {
        return tokenCache.clearJengaToken()
    }

    override fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable {
        return tokenCache.saveJengaToken(jengaTokenEntity)
                .andThen(tokenCache.setExpireTime(jengaTokenEntity.expiresIn.toLong()))
    }

    override fun getJengaToken(): Flowable<JengaTokenEntity> {
        return tokenCache.getJengaToken()
    }


}