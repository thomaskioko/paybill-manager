package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import javax.inject.Inject

open class JengaTokenMapper @Inject constructor() : EntityMapper<JengaTokenEntity, JengaToken> {

    override fun mapFromEntity(entity: JengaTokenEntity): JengaToken {
        return JengaToken(entity.tokenType, entity.issuedAt, entity.expiresIn, entity.accessToken)
    }

    override fun mapToEntity(domain: JengaToken): JengaTokenEntity {
        return JengaTokenEntity(domain.tokenType, domain.issuedAt, domain.expiresIn, domain.accessToken)
    }

}