package com.lekaha.simpletube.remote.test.factory

import com.lekaha.simpletube.remote.model.SimpletubeModel
import com.lekaha.simpletube.remote.test.factory.DataFactory.Factory.randomLong
import com.lekaha.simpletube.remote.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Simpletube related instances
 */
class SimpletubeFactory {

    companion object Factory {

        fun makeVideoListResponse() = makeSimpletubeModelList(5)

        fun makeSimpletubeModelList(count: Int): List<SimpletubeModel> {
            val simpletubeEntities = mutableListOf<SimpletubeModel>()
            repeat(count) {
                simpletubeEntities.add(makeSimpletubeModel())
            }
            return simpletubeEntities
        }

        fun makeSimpletubeModel(): SimpletubeModel {
            return SimpletubeModel(
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