package com.brodgate.marvelapi.repository

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*


object MarvelService {

    private val tag = MarvelService::class.java.name
    private const val baseUrl = "gateway.marvel.com"

    val httpClient = HttpClient{
        install(ContentNegotiation){
           json(kotlinx.serialization.json.Json {
               prettyPrint = true
               isLenient = true
               ignoreUnknownKeys = true
               encodeDefaults = true
           })
        }
        install(HttpRequestRetry){
            retryOnException(maxRetries = 5)
            retryOnServerErrors(maxRetries = 5)
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
        install(HttpRequestRetry)
        defaultRequest {
            contentType(Json)
            accept(Json)
            host = baseUrl
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}