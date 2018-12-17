package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.model.TokenView


object TokenFactory {

    fun makeSafaricomTokenView(): TokenView {
        return TokenView(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
    }

    fun makeJengaToken(): JengaToken {
        return JengaToken("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeJengaTokenView(): JengaTokenView {
        return JengaTokenView("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

}