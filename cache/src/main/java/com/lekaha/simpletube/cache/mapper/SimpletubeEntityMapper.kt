package com.lekaha.simpletube.cache.mapper

import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.data.model.SimpletubeEntity

/**
 * Map a [CachedSimpletube] instance to and from a [SimpletubeEntity] instance when data is moving between
 * this later and the Data layer
 */
class SimpletubeEntityMapper : EntityMapper<CachedSimpletube, SimpletubeEntity> {

    /**
     * Map a [SimpletubeEntity] instance to a [CachedSimpletube] instance
     */
    override fun mapToCached(type: SimpletubeEntity): CachedSimpletube {
        return CachedSimpletube(
            type.name,
            type.title,
            type.thumbnailUrl,
            type.description,
            type.videoUrl,
            type.videoDuration
        )
    }

    /**
     * Map a [CachedSimpletube] instance to a [SimpletubeEntity] instance
     */
    override fun mapFromCached(type: CachedSimpletube): SimpletubeEntity {
        return SimpletubeEntity(
            type.name,
            type.title,
            type.thumbnailUrl,
            type.description,
            type.videoUrl,
            type.videoDuration
        )
    }

}