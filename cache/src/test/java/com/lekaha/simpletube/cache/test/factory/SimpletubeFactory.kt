package com.lekaha.simpletube.cache.test.factory

import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.cache.test.factory.DataFactory.Factory.randomLong
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.cache.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeCachedSimpletube(): CachedSimpletube {
            return CachedSimpletube(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomLong()
            )
        }

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

        fun makeSimpletubeEntityList(count: Int): List<SimpletubeEntity> {
            val simpletubeEntities = mutableListOf<SimpletubeEntity>()
            repeat(count) {
                simpletubeEntities.add(makeSimpletubeEntity())
            }
            return simpletubeEntities
        }

        fun makeCachedSimpletubeList(count: Int): List<CachedSimpletube> {
            val cachedSimpletubes = mutableListOf<CachedSimpletube>()
            repeat(count) {
                cachedSimpletubes.add(makeCachedSimpletube())
            }
            return cachedSimpletubes
        }

    }

}