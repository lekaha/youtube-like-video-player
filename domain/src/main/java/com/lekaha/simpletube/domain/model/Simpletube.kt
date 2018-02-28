package com.lekaha.simpletube.domain.model

/**
 * Representation for a [Simpletube] fetched from an external layer data source
 */
data class Simpletube(
    val name: String,
    val title: String,
    val thumbnailUrl: String,
    val description: String,
    val videoUrl: String,
    val videoDuration: Long
    )