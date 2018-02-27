package com.lekaha.simpletube.data.mapper

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.domain.model.Simpletube


/**
 * Map a [SimpletubeEntity] to and from a [Simpletube] instance when data is moving between
 * this later and the Domain layer
 */
open class SimpletubeMapper : Mapper<SimpletubeEntity, Simpletube> {

    /**
     * Map a [SimpletubeEntity] instance to a [Simpletube] instance
     */
    override fun mapFromEntity(type: SimpletubeEntity): Simpletube {
        return Simpletube(type.name, type.title, type.avatar)
    }

    /**
     * Map a [Simpletube] instance to a [SimpletubeEntity] instance
     */
    override fun mapToEntity(type: Simpletube): SimpletubeEntity {
        return SimpletubeEntity(type.name, type.title, type.avatar)
    }


}