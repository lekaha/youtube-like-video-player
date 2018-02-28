package com.lekaha.simpletube.domain.test.factory

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.test.factory.DataFactory.Factory.randomLong
import com.lekaha.simpletube.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeSimpletubeList(count: Int): List<Simpletube> {
            val simpletubes = mutableListOf<Simpletube>()
            repeat(count) {
                simpletubes.add(makeSimpletube())
            }
            return simpletubes
        }

        fun makeSimpletube(): Simpletube {
            return Simpletube(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomLong()
            )
        }

    }

}