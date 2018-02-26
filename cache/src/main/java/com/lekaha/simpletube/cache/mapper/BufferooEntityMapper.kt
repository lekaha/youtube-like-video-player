package com.lekaha.simpletube.cache.mapper

import com.lekaha.simpletube.cache.model.CachedBufferoo
import com.lekaha.simpletube.data.model.BufferooEntity

/**
 * Map a [CachedBufferoo] instance to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
class BufferooEntityMapper : EntityMapper<CachedBufferoo, BufferooEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: BufferooEntity): CachedBufferoo {
        return CachedBufferoo(type.name, type.title, type.avatar)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedBufferoo): BufferooEntity {
        return BufferooEntity(type.name, type.title, type.avatar)
    }

}