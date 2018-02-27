package com.lekaha.simpletube.ui

import com.lekaha.simpletube.ui.mapper.SimpletubeMapper
import com.lekaha.simpletube.ui.test.factory.SimpletubeFactory
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
    fun mapToViewMapsData() {
        val simpletubeView = SimpletubeFactory.makeSimpletubeView()
        val simpletubeViewModel = simpletubeMapper.mapToViewModel(simpletubeView)

        assertEquals(simpletubeView.name, simpletubeViewModel.name)
        assertEquals(simpletubeView.title, simpletubeViewModel.title)
        assertEquals(simpletubeView.avatar, simpletubeViewModel.avatar)
    }

}