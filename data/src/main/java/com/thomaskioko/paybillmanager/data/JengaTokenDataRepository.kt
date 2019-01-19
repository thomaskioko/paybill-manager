package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import io.reactivex.Flowable
import javax.inject.Inject

class JengaTokenDataRepository @Inject constructor(
        private val mapper: JengaTokenMapper,
        private val cache: JengaTokenCache,
        private val factory: JengaTokenDataStoreFactory
) : JengaTokenRepository {

    override fun getJengaToken(): Flowable<JengaToken> {
        return cache.hasTokenExpired()
                .flatMapPublisher {
                    factory.getDataStore(it)
                            .getJengaToken()
                            .distinctUntilChanged()
                }
                .flatMap {
                    factory.getCacheDataStore()
                            .saveJengaToken(it)
                            .andThen(Flowable.just(it))
                }
                .map {
                    mapper.mapFromEntity(it)
                }
    }
}