package com.lekaha.simpletube.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.lekaha.simpletube.data.repository.SimpletubeCache
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpletubeDataStoreFactoryTest {

    private lateinit var simpletubeDataStoreFactory: SimpletubeDataStoreFactory

    private lateinit var simpletubeCache: SimpletubeCache
    private lateinit var simpletubeCacheDataStore: SimpletubeCacheDataStore
    private lateinit var simpletubeRemoteDataStore: SimpletubeRemoteDataStore

    @Before
    fun setUp() {
        simpletubeCache = mock()
        simpletubeCacheDataStore = mock()
        simpletubeRemoteDataStore = mock()
        simpletubeDataStoreFactory = SimpletubeDataStoreFactory(
            simpletubeCache,
            simpletubeCacheDataStore, simpletubeRemoteDataStore
        )
    }
    
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubSimpletubeCacheIsCached(false)
        val simpletubeDataStore = simpletubeDataStoreFactory.retrieveDataStore()
        assert(simpletubeDataStore is SimpletubeRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubSimpletubeCacheIsCached(true)
        stubSimpletubeCacheIsExpired(true)
        val simpletubeDataStore = simpletubeDataStoreFactory.retrieveDataStore()
        assert(simpletubeDataStore is SimpletubeRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubSimpletubeCacheIsCached(true)
        stubSimpletubeCacheIsExpired(false)
        val simpletubeDataStore = simpletubeDataStoreFactory.retrieveDataStore()
        assert(simpletubeDataStore is SimpletubeCacheDataStore)
    }
    
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val simpletubeDataStore = simpletubeDataStoreFactory.retrieveRemoteDataStore()
        assert(simpletubeDataStore is SimpletubeRemoteDataStore)
    }
    
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val simpletubeDataStore = simpletubeDataStoreFactory.retrieveCacheDataStore()
        assert(simpletubeDataStore is SimpletubeCacheDataStore)
    }
    
    private fun stubSimpletubeCacheIsCached(isCached: Boolean) {
        whenever(simpletubeCache.isCached())
                .thenReturn(isCached)
    }

    private fun stubSimpletubeCacheIsExpired(isExpired: Boolean) {
        whenever(simpletubeCache.isExpired())
                .thenReturn(isExpired)
    }
    
}