package com.lekaha.simpletube.presentation.mapper

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSection
import com.lekaha.simpletube.presentation.model.SimpletubeSectionView
import com.lekaha.simpletube.presentation.model.SimpletubeView

/**
 * Map a [SimpletubeSectionView] to and from a [SimpletubeSection] instance when data is
 * moving between this layer and the Domain layer
 */
open class SimpletubeSectionMapper : Mapper<SimpletubeSectionView, SimpletubeSection> {

    /**
     * Map a [Simpletube] instance to a [SimpletubeView] instance
     */
    override fun mapToView(type: SimpletubeSection): SimpletubeSectionView {
        return SimpletubeSectionView(type.id, type.title, type.description)
    }


}