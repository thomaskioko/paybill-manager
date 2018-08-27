package com.thomaskioko.paybillmanager.domain.model

data class SafaricomToken(
        var id: Int,
        var expiresIn: String,
        var accessToken: String
)