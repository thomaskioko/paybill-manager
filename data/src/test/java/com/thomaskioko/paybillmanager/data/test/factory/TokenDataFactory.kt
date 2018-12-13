package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken

object TokenDataFactory {

    fun makeSafaricomToken(): SafaricomToken {
        return SafaricomToken(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
    }

    fun makeSafaricomTokenEntity(): SafaricomTokenEntity {
        return SafaricomTokenEntity(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
    }


    fun makeJengaToken(): JengaToken {
        return JengaToken("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }


    fun makeJengaTokenEntity(): JengaTokenEntity {
        return JengaTokenEntity("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }


    fun makeSafaricomTokenList(count: Int): List<SafaricomToken> {
        val tokenList = mutableListOf<SafaricomToken>()
        repeat(count) {
            tokenList.add(makeSafaricomToken())
        }
        return tokenList
    }

    fun makeSafaricomTokenEntityList(count: Int): List<SafaricomTokenEntity> {
        val entities = mutableListOf<SafaricomTokenEntity>()
        repeat(count) {
            entities.add(makeSafaricomTokenEntity())
        }
        return entities
    }

}