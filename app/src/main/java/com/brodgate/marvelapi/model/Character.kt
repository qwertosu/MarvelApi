package com.brodgate.marvelapi.model
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName



@Serializable
data class Character(
    @SerialName("attributionHTML")
    val attributionHTML: String?,
    @SerialName("attributionText")
    val attributionText: String?,
    @SerialName("code")
    val code: Int?,
    @SerialName("copyright")
    val copyright: String?,
    @SerialName("data")
    val data : Data?,
    @SerialName("etag")
    val etag: String?,
    @SerialName("status")
    val status: String?
)

@Serializable
data class Data(
    @SerialName("count")
    val count: Int?,
    @SerialName("limit")
    val limit: Int?,
    @SerialName("offset")
    val offset: Int?,
    @SerialName("results")
    val results: List<Result>?,
    @SerialName("total")
    val total: Int?
)

@Serializable
data class Result(
    @SerialName("comics")
    val comics: Comics? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("events")
    val events: Events? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("modified")
    val modified: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("resourceURI")
    val resourceURI: String? = null,
    @SerialName("series")
    val series: Series? = null,
    @SerialName("stories")
    val stories: Stories? = null,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerialName("urls")
    val urls: List<Url>? = null
)

@Serializable
data class Comics(
    @SerialName("available")
    val available: Int?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<Item>?,
    @SerialName("returned")
    val returned: Int?
)

@Serializable
data class Events(
    @SerialName("available")
    val available: Int?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<ItemX>?,
    @SerialName("returned")
    val returned: Int?
)

@Serializable
data class Series(
    @SerialName("available")
    val available: Int?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<ItemXX>?,
    @SerialName("returned")
    val returned: Int?
)

@Serializable
data class Stories(
    @SerialName("available")
    val available: Int?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<ItemXXX>?,
    @SerialName("returned")
    val returned: Int?
)

@Serializable
data class Thumbnail(
    @SerialName("extension")
    val extension: String?,
    @SerialName("path")
    val path: String?
)

@Serializable
data class Url(
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?
)

@Serializable
data class Item(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?
)

@Serializable
data class ItemX(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?
)

@Serializable
data class ItemXX(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?
)

@Serializable
data class ItemXXX(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?,
    @SerialName("type")
    val type: String?
)