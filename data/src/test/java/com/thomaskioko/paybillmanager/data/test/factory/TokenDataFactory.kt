package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken

object TokenDataFactory {

    fun makeSafaricomToken(): SafaricomToken {
        return SafaricomToken(DataFactory.randomInt(), DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeSafaricomTokenEntity(): SafaricomTokenEntity {
        return SafaricomTokenEntity(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
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