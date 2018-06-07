package com.thomaskioko.safaricomapi.util

import com.thomaskioko.daraja.repository.db.entity.SafaricomToken

object TestUtil {


    fun createToken() = SafaricomToken("3599", "jK13flUwxNhIsYPzvNAJwslDnruQ")

    fun createToken(token: String) = SafaricomToken("3599", token)
}