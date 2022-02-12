package com.brodgate.marvelapi.repository

import com.brodgate.marvelapi.model.Character
import io.ktor.client.request.*

object CharacterDataManager {

    private const val apikey = "4e43f487b31de3161d7144edcdb0c3ba"
    private const val hash = "a68446ce3ec59800747eebd3c0c0b945"

    suspend fun getCharacters(): Character {
        val path = "v1/public/characters"
        MarvelService.httpClient.use {
            val data = it.get<Character>(path) {
                parameter("apikey", apikey)
                parameter("hash", hash)
                parameter("ts", "1")
                parameter("limit", "100")
                parameter("offset", "4")
            }
            return data
        }
    }


}