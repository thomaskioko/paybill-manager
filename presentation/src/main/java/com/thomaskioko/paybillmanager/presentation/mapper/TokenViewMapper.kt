package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.presentation.model.TokenView
import javax.inject.Inject

open class TokenViewMapper @Inject constructor() : Mapper<TokenView, SafaricomToken> {

    override fun mapToView(type: SafaricomToken): TokenView {
        return TokenView(type.id, type.expiresIn, type.accessToken)
    }
}