package com.lekaha.simpletube.data.test.factory

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.test.factory.DataFactory.Factory.randomLong
import com.lekaha.simpletube.data.test.factory.DataFactory.Factory.randomUuid
import com.lekaha.simpletube.domain.model.Simpletube

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeSimpletubeEntity(): SimpletubeEntity {
            return SimpletubeEntity(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomLong()
            )
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

        fun makeSimpletubeEntityList(count: Int): List<SimpletubeEntity> {
            val simpletubeEntities = mutableListOf<SimpletubeEntity>()
            repeat(count) {
                simpletubeEntities.add(makeSimpletubeEntity())
            }
            return simpletubeEntities
        }

        fun makeSimpletubeList(count: Int): List<Simpletube> {
            val simpletubes = mutableListOf<Simpletube>()
            repeat(count) {
                simpletubes.add(makeSimpletube())
            }
            return simpletubes
        }

    }

}