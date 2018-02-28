package com.lekaha.simpletube.remote.mapper

import com.lekaha.simpletube.remote.test.factory.SimpletubeFactory
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
    fun mapFromRemoteMapsData() {
        val simpletubeModel = SimpletubeFactory.makeSimpletubeModel()
        val simpletubeEntity = simpletubeEntityMapper.mapToData(simpletubeModel)

        assertEquals(simpletubeModel.presenterName, simpletubeEntity.name)
        assertEquals(simpletubeModel.title, simpletubeEntity.title)
        assertEquals(simpletubeModel.thumbnailUrl, simpletubeEntity.thumbnailUrl)
    }

}