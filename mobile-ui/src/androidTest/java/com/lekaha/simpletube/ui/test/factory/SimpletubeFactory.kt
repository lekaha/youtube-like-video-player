package com.lekaha.simpletube.ui.test.factory

import com.lekaha.simpletube.domain.model.Simpletube

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
            return Simpletube(DataFactory.randomUuid(), DataFactory.randomUuid(),
                    DataFactory.randomUuid())
        }

    }

}