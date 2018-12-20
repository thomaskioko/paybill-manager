package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class JengaTokenDataRepository @Inject constructor(
        private val mapper: JengaTokenMapper,
        private val cache: JengaTokenCache,
        private val factory: JengaTokenDataStoreFactory
) : JengaTokenRepository {

    override fun getJengaToken(): Observable<JengaToken> {
        return cache.hasTokenExpired().toObservable()
                .flatMap {
                    factory.getRemoteDataStore()
                            .getJengaToken().toObservable()
                            .distinctUntilChanged()
                }c
                .flatMap { jengaToken ->
                    factory.getCacheDataStore()
                            .saveJengaToken(jengaToken)
                            .andThen(Observable.just(jengaToken))
                }
                .map {
                    mapper.mapFromEntity(it)
                }
    }
}