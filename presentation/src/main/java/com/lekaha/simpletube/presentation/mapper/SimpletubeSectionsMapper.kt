package com.lekaha.simpletube.presentation.mapper

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSection
import com.lekaha.simpletube.domain.model.SimpletubeSections
import com.lekaha.simpletube.presentation.model.SimpletubeSectionView
import com.lekaha.simpletube.presentation.model.SimpletubeSectionsView
import com.lekaha.simpletube.presentation.model.SimpletubeView

/**
 * Map a [SimpletubeSectionView] to and from a [SimpletubeSection] instance when data is
 * moving between this layer and the Domain layer
 */
open class SimpletubeSectionsMapper : Mapper<SimpletubeSectionsView, SimpletubeSections> {

    /**
     * Map a [Simpletube] instance to a [SimpletubeView] instance
     */
    private fun mapToView(type: SimpletubeSection): SimpletubeSectionView {
        return SimpletubeSectionView(type.id, type.title, type.description)
    }

    /**
     * Map a [Simpletube] instance to a [SimpletubeView] instance
     */
    override fun mapToView(type: SimpletubeSections): SimpletubeSectionsView {
        return SimpletubeSectionsView(
            type.simpletube.name,
            type.simpletube.title,
            type.simpletube.thumbnailUrl,
            type.simpletube.videoUrl,
            type.simpletube.videoDuration,
            type.sections.map { mapToView(it) }
        )
    }


}