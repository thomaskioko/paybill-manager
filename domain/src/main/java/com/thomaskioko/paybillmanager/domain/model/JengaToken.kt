package com.thomaskioko.paybillmanager.domain.model

data class JengaToken(
        val tokenType: String,
        val issuedAt: String,
        val expiresIn: String,
        val accessToken: String
)
