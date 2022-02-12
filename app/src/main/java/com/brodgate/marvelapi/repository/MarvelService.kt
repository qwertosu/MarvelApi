package com.brodgate.marvelapi.repository

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object MarvelService {

    private val tag = MarvelService::class.java.name
    private val baseUrl = "gateway.marvel.com"
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys =true
    }

    val httpClient = HttpClient{
        install(JsonFeature){
            serializer = KotlinxSerializer(json)
        }
        install(Logging){
            logger = object : Logger{
                override fun log(message: String) {
                    println("$tag log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install (HttpTimeout){
            socketTimeoutMillis = 30_000
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            host = baseUrl
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}