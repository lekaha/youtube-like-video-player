package com.lekaha.simpletube.ui.test.factory

import com.lekaha.simpletube.presentation.model.SimpletubeView
import com.lekaha.simpletube.ui.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeSimpletubeView(): SimpletubeView {
            return SimpletubeView(randomUuid(), randomUuid(), randomUuid())
        }

    }

}