package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import javax.inject.Inject

open class TokenMapper @Inject constructor() : EntityMapper<SafaricomTokenEntity, SafaricomToken> {

    override fun mapFromEntity(entity: SafaricomTokenEntity): SafaricomToken {
        return SafaricomToken(entity.id, entity.expiresIn, entity.accessToken)
    }

    override fun mapToEntity(domain: SafaricomToken): SafaricomTokenEntity {
        return SafaricomTokenEntity(domain.id, domain.expiresIn, domain.accessToken)
    }

}