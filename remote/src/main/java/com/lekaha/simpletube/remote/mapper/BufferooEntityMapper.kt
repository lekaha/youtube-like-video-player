package com.lekaha.simpletube.remote.mapper

import com.lekaha.simpletube.data.model.BufferooEntity
import com.lekaha.simpletube.remote.model.BufferooModel

/**
 * Map a [BufferooModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class BufferooEntityMapper : EntityMapper<BufferooModel, BufferooEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [BufferooEntity] model
     */
    override fun mapToData(type: BufferooModel) =
        BufferooEntity(type.name, type.title, type.avatar)

    override fun mapFromData(type: BufferooEntity) = TODO()
}