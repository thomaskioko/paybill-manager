package com.thomaskioko.paybillmanager.data.model

data class JengaTokenEntity(
        val tokenType: String,
        val issuedAt: String,
        val expiresIn: String,
        val accessToken: String
)
