package com.thomaskioko.paybillmanager.remote.mapper


import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.remote.model.JengaToken
import javax.inject.Inject

open class JengaTokenMapper @Inject constructor() : ModelMapper<JengaToken, JengaTokenEntity> {

    override fun mapFromModel(model: JengaToken): JengaTokenEntity {
        return JengaTokenEntity(model.tokenType, model.issuedAt, model.expiresIn, model.accessToken)
    }

}