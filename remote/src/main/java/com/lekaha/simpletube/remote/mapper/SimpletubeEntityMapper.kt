package com.lekaha.simpletube.remote.mapper

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.remote.model.SimpletubeModel

/**
 * Map a [SimpletubeModel] to and from a [SimpletubeEntity] instance when data is moving between
 * this later and the Data layer
 */
open class SimpletubeEntityMapper : EntityMapper<SimpletubeModel, SimpletubeEntity> {

    /**
     * Map an instance of a [SimpletubeModel] to a [SimpletubeEntity] model
     */
    override fun mapToData(type: SimpletubeModel) =
        SimpletubeEntity(type.presenterName, type.title, type.thumbnailUrl)

    override fun mapFromData(type: SimpletubeEntity) = TODO()
}