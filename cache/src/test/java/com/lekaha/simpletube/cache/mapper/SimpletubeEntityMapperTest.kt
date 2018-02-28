package com.lekaha.simpletube.cache.mapper

import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.cache.test.factory.SimpletubeFactory
import com.lekaha.simpletube.data.model.SimpletubeEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SimpletubeEntityMapperTest {

    private lateinit var simpletubeEntityMapper: SimpletubeEntityMapper

    @Before
    fun setUp() {
        simpletubeEntityMapper = SimpletubeEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val simpletubeEntity = SimpletubeFactory.makeSimpletubeEntity()
        val cachedSimpletube = simpletubeEntityMapper.mapToCached(simpletubeEntity)

        assertSimpletubeDataEquality(simpletubeEntity, cachedSimpletube)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedSimpletube = SimpletubeFactory.makeCachedSimpletube()
        val simpletubeEntity = simpletubeEntityMapper.mapFromCached(cachedSimpletube)

        assertSimpletubeDataEquality(simpletubeEntity, cachedSimpletube)
    }

    private fun assertSimpletubeDataEquality(
        simpletubeEntity: SimpletubeEntity,
        cachedSimpletube: CachedSimpletube
    ) {
        assertEquals(simpletubeEntity.name, cachedSimpletube.name)
        assertEquals(simpletubeEntity.title, cachedSimpletube.title)
        assertEquals(simpletubeEntity.thumbnailUrl, cachedSimpletube.thumbnailUrl)
        assertEquals(simpletubeEntity.description, cachedSimpletube.description)
        assertEquals(simpletubeEntity.videoUrl, cachedSimpletube.videoUrl)
        assertEquals(simpletubeEntity.videoDuration, cachedSimpletube.videoDuration)
    }

}