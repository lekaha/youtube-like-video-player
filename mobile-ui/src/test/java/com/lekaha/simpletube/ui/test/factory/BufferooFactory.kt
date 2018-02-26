package com.lekaha.simpletube.ui.test.factory

import com.lekaha.simpletube.presentation.model.BufferooView
import com.lekaha.simpletube.ui.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooView(): BufferooView {
            return BufferooView(randomUuid(), randomUuid(), randomUuid())
        }

    }

}