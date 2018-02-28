package com.lekaha.simpletube.data.model

/**
 * Representation for a [SimpletubeEntity] fetched from an external layer data source
 */
data class SimpletubeEntity(
    val name: String,
    val title: String,
    val thumbnailUrl: String,
    val description: String,
    val videoUrl: String,
    val videoDuration: Long
)