package com.thomaskioko.paybillmanager.data.model

data class SafaricomTokenEntity(
        var id: Int,
        var expiresIn: Long,
        var accessToken: String
)