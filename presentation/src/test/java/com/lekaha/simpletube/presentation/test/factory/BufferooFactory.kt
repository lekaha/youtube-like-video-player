package com.lekaha.simpletube.presentation.test.factory

import com.lekaha.simpletube.domain.model.Bufferoo
import com.lekaha.simpletube.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferooModel())
            }
            return bufferoos
        }

        fun makeBufferooModel(): Bufferoo {
            return Bufferoo(randomUuid(), randomUuid(), randomUuid())
        }

    }

}