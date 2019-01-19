package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import javax.inject.Inject

open class JengaTokenViewMapper @Inject constructor() : Mapper<JengaTokenView, JengaToken> {

    override fun mapToView(type: JengaToken): JengaTokenView {
        return JengaTokenView(type.tokenType, type.issuedAt, type.expiresIn, type.accessToken)
    }
}