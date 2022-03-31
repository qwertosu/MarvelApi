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

    //

    companion object {
        private const val apikey = "360a7db68f03039846e053a6c615ea7a"
        private const val hash = "d7114228e5923141d133e7f5190e7872"
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