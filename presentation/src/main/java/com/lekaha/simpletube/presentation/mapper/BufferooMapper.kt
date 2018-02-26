package com.lekaha.simpletube.presentation.mapper

import com.lekaha.simpletube.domain.model.Bufferoo
import com.lekaha.simpletube.presentation.model.BufferooView

/**
 * Map a [BufferooView] to and from a [Bufferoo] instance when data is moving between
 * this layer and the Domain layer
 */
open class BufferooMapper : Mapper<BufferooView, Bufferoo> {

    /**
     * Map a [Bufferoo] instance to a [BufferooView] instance
     */
    override fun mapToView(type: Bufferoo): BufferooView {
        return BufferooView(type.name, type.title, type.avatar)
    }


}