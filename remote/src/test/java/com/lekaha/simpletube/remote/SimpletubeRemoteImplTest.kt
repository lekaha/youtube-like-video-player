package com.lekaha.simpletube.remote

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.remote.mapper.SimpletubeEntityMapper
import com.lekaha.simpletube.remote.model.SimpletubeModel
import com.lekaha.simpletube.remote.test.factory.SimpletubeFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpletubeRemoteImplTest {

    private lateinit var entityMapper: SimpletubeEntityMapper
    private lateinit var simpletubeService: SimpletubeService

    private lateinit var simpletubeRemoteImpl: SimpletubeRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        simpletubeService = mock()
        simpletubeRemoteImpl = SimpletubeRemoteImpl(simpletubeService, entityMapper)
    }

    @Test
    fun getSimpletubesCompletes() {
        stubSimpletubeServiceGetSimpletubes(Single.just(SimpletubeFactory.makeVideoListResponse()))
        val testObserver = simpletubeRemoteImpl.getVideos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSimpletubesReturnsData() {
        val videoListResponse = SimpletubeFactory.makeVideoListResponse()
        stubSimpletubeServiceGetSimpletubes(Single.just(videoListResponse))
        val simpletubeEntities = mutableListOf<SimpletubeEntity>()
        videoListResponse.forEach {
            simpletubeEntities.add(entityMapper.mapToData(it))
        }

        val testObserver = simpletubeRemoteImpl.getVideos().test()
        testObserver.assertValue(simpletubeEntities)
    }

    private fun stubSimpletubeServiceGetSimpletubes(single: Single<List<SimpletubeModel>>) {
        whenever(simpletubeService.getVideos())
            .thenReturn(single)
    }
}