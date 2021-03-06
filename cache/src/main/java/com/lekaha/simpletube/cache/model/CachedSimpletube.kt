package com.lekaha.simpletube.cache.model

/**
 * Model used solely for the caching of a simpletube
 */
data class CachedSimpletube(
    val name: String,
    val title: String,
    val thumbnailUrl: String,
    val description: String,
    val videoUrl: String,
    val videoDuration: Long
)