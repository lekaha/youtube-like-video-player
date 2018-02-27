package com.lekaha.simpletube.presentation.test.factory

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeSimpletubeList(count: Int): List<Simpletube> {
            val simpletubes = mutableListOf<Simpletube>()
            repeat(count) {
                simpletubes.add(makeSimpletubeModel())
            }
            return simpletubes
        }

        fun makeSimpletubeModel(): Simpletube {
            return Simpletube(randomUuid(), randomUuid(), randomUuid())
        }

    }

}