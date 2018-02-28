package com.lekaha.simpletube.remote.model

/**
 * Representation for a [SimpletubeModel] fetched from the API
 */
data class SimpletubeModel(
    val title: String,
    val presenterName: String,
    val thumbnailUrl: String,
    val description: String,
    val videoUrl: String,
    val videoDuration: Long
)