package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.presentation.model.TokenView


object TokenFactory {

    fun makeSafaricomToken(): SafaricomToken {
        return SafaricomToken(DataFactory.randomInt(), DataFactory.randomUuid(), DataFactory.randomUuid())
    }


    fun makeSafaricomTokenView(): TokenView {
        return TokenView(DataFactory.randomInt(), DataFactory.randomUuid(), DataFactory.randomUuid())
    }

}