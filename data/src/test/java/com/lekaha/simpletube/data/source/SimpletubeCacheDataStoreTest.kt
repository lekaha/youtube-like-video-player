package com.lekaha.simpletube.data.source

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeCache
import com.lekaha.simpletube.data.test.factory.SimpletubeFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpletubeCacheDataStoreTest {

    private lateinit var simpletubeCacheDataStore: SimpletubeCacheDataStore

    private lateinit var simpletubeCache: SimpletubeCache

    @Before
    fun setUp() {
        simpletubeCache = mock()
        simpletubeCacheDataStore = SimpletubeCacheDataStore(simpletubeCache)
    }

    @Test
    fun clearSimpletubesCompletes() {
        stubSimpletubeCacheClearSimpletubes(Completable.complete())
        val testObserver = simpletubeCacheDataStore.clearSimpletubes().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveSimpletubesCompletes() {
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        val testObserver = simpletubeCacheDataStore.saveSimpletubes(
                SimpletubeFactory.makeSimpletubeEntityList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSimpletubesCompletes() {
        stubSimpletubeCacheGetSimpletubes(Single.just(SimpletubeFactory.makeSimpletubeEntityList(2)))
        val testObserver = simpletubeCacheDataStore.getSimpletubes().test()
        testObserver.assertComplete()
    }

    private fun stubSimpletubeCacheSaveSimpletubes(completable: Completable) {
        whenever(simpletubeCache.saveSimpletubes(any()))
                .thenReturn(completable)
    }

    private fun stubSimpletubeCacheGetSimpletubes(single: Single<List<SimpletubeEntity>>) {
        whenever(simpletubeCache.getSimpletubes())
                .thenReturn(single)
    }

    private fun stubSimpletubeCacheClearSimpletubes(completable: Completable) {
        whenever(simpletubeCache.clearSimpletubes())
                .thenReturn(completable)
    }

}