package com.thomaskioko.paybillmanager.remote.mapper


import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.remote.model.SafaricomTokenModel
import javax.inject.Inject

open class TokenResponseModelMapper @Inject constructor(): ModelMapper<SafaricomTokenModel, SafaricomTokenEntity> {

    override fun mapFromModel(model: SafaricomTokenModel): SafaricomTokenEntity {
        return SafaricomTokenEntity(Math.random().toInt(), model.expiresIn.toLong(), model.accessToken )
    }

}