package com.brodgate.marvelapi.repository

import com.brodgate.marvelapi.model.Character
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class CharacterApiDataManager {

    companion object {
        private const val apikey = "4e43f487b31de3161d7144edcdb0c3ba"
        private const val hash = "a68446ce3ec59800747eebd3c0c0b945"
        const val characterPath = "v1/public/characters"
    }

    suspend fun getCharacters(offset : Int = 0): Character = coroutineScope {
        MarvelService.httpClient.use {
            val data = it.get(characterPath) {
                headers {
                    append(HttpHeaders.ContentType, "application/json; charset=utf-8")
                }
                parameter("apikey", apikey)
                parameter("hash", hash)
                parameter("ts", "1")
                parameter("limit", "100")
                parameter("offset", "$offset")
            }.body<Character>()
            return@coroutineScope data
        }
    }
}