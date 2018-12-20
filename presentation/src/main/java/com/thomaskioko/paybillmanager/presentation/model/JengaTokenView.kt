package com.thomaskioko.paybillmanager.presentation.model

class JengaTokenView(
        val tokenType: String,
        val issuedAt: String,
        val expiresIn: String,
        val accessToken: String
)