package com.lekaha.simpletube.data.mapper

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.test.factory.SimpletubeFactory
import com.lekaha.simpletube.domain.model.Simpletube
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SimpletubeMapperTest {

    private lateinit var simpletubeMapper: SimpletubeMapper

    @Before
    fun setUp() {
        simpletubeMapper = SimpletubeMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        val simpletubeEntity = SimpletubeFactory.makeSimpletubeEntity()
        val simpletube = simpletubeMapper.mapFromEntity(simpletubeEntity)

        assertSimpletubeDataEquality(simpletubeEntity, simpletube)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedSimpletube = SimpletubeFactory.makeSimpletube()
        val simpletubeEntity = simpletubeMapper.mapToEntity(cachedSimpletube)

        assertSimpletubeDataEquality(simpletubeEntity, cachedSimpletube)
    }

    private fun assertSimpletubeDataEquality(
        simpletubeEntity: SimpletubeEntity,
                                           simpletube: Simpletube
    ) {
        assertEquals(simpletubeEntity.name, simpletube.name)
        assertEquals(simpletubeEntity.title, simpletube.title)
        assertEquals(simpletubeEntity.thumbnailUrl, simpletube.thumbnailUrl)
    }

}