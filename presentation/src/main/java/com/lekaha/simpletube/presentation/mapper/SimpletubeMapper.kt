package com.lekaha.simpletube.presentation.mapper

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.presentation.model.SimpletubeView

/**
 * Map a [SimpletubeView] to and from a [Simpletube] instance when data is moving between
 * this layer and the Domain layer
 */
open class SimpletubeMapper : Mapper<SimpletubeView, Simpletube> {

    /**
     * Map a [Simpletube] instance to a [SimpletubeView] instance
     */
    override fun mapToView(type: Simpletube): SimpletubeView {
        return SimpletubeView(type.name, type.title, type.thumbnailUrl)
    }


}