package com.lekaha.simpletube.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeRemote
import com.lekaha.simpletube.data.test.factory.SimpletubeFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpletubeRemoteDataStoreTest {

    private lateinit var simpletubeRemoteDataStore: SimpletubeRemoteDataStore

    private lateinit var simpletubeRemote: SimpletubeRemote

    @Before
    fun setUp() {
        simpletubeRemote = mock()
        simpletubeRemoteDataStore = SimpletubeRemoteDataStore(simpletubeRemote)
    }
    
    @Test(expected = UnsupportedOperationException::class)
    fun clearSimpletubesThrowsException() {
        simpletubeRemoteDataStore.clearSimpletubes().test()
    }
    
    @Test(expected = UnsupportedOperationException::class)
    fun saveSimpletubesThrowsException() {
        simpletubeRemoteDataStore.saveSimpletubes(SimpletubeFactory.makeSimpletubeEntityList(2)).test()
    }
    
    @Test
    fun getSimpletubesCompletes() {
        stubSimpletubeCacheGetSimpletubes(Single.just(SimpletubeFactory.makeSimpletubeEntityList(2)))
        val testObserver = simpletubeRemote.getVideos().test()
        testObserver.assertComplete()
    }
    
    private fun stubSimpletubeCacheGetSimpletubes(single: Single<List<SimpletubeEntity>>) {
        whenever(simpletubeRemote.getVideos())
                .thenReturn(single)
    }

}