package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.TokenMapper
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import com.thomaskioko.paybillmanager.data.store.token.TokenDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.domain.repository.TokenRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TokenDataRepository @Inject constructor(
        private val mapper: TokenMapper,
        private val cache: TokenCache,
        private val factory: TokenDataStoreFactory
) : TokenRepository {

    override fun getToken(): Observable<SafaricomToken> {
        return Observable.zip(cache.isTokenCached().toObservable(),
                cache.hasTokenExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getSafaricomToken().toObservable()
                            .distinctUntilChanged()
                }
                .flatMap { safaricomToken ->
                    factory.getCacheDataStore()
                            .saveSafaricomToken(safaricomToken)
                            .andThen(Observable.just(safaricomToken))
                }
                .map {
                    mapper.mapFromEntity(it)
                }
    }

    override fun saveToken(safaricomToken: SafaricomToken): Completable {
        return factory.getCacheDataStore().saveSafaricomToken(mapper.mapToEntity(safaricomToken))
    }
}