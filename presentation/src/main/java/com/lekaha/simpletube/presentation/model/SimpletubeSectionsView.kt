package com.lekaha.simpletube.presentation.model

/**
 * Representation for a [SimpletubeSectionsView] instance for this layers Model representation
 */
data class SimpletubeSectionsView(
    val name: String,
    val title: String,
    val thumbnailUrl: String,
    var videoUrl: String,
    var videoDuration: Long,
    var sections: List<SimpletubeSectionView>
)