package com.lekaha.simpletube.data

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Single
import com.lekaha.simpletube.data.mapper.SimpletubeMapper
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeDataStore
import com.lekaha.simpletube.data.source.SimpletubeCacheDataStore
import com.lekaha.simpletube.data.source.SimpletubeDataStoreFactory
import com.lekaha.simpletube.data.source.SimpletubeRemoteDataStore
import com.lekaha.simpletube.data.test.factory.SimpletubeFactory
import com.lekaha.simpletube.domain.model.Simpletube
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpletubeDataRepositoryTest {

    private lateinit var simpletubeDataRepository: SimpletubeDataRepository

    private lateinit var simpletubeDataStoreFactory: SimpletubeDataStoreFactory
    private lateinit var simpletubeMapper: SimpletubeMapper
    private lateinit var simpletubeCacheDataStore: SimpletubeCacheDataStore
    private lateinit var simpletubeRemoteDataStore: SimpletubeRemoteDataStore

    @Before
    fun setUp() {
        simpletubeDataStoreFactory = mock()
        simpletubeMapper = mock()
        simpletubeCacheDataStore = mock()
        simpletubeRemoteDataStore = mock()
        simpletubeDataRepository = SimpletubeDataRepository(simpletubeDataStoreFactory,
            simpletubeMapper
        )
        stubSimpletubeDataStoreFactoryRetrieveCacheDataStore()
        stubSimpletubeDataStoreFactoryRetrieveRemoteDataStore()
    }
    
    @Test
    fun clearSimpletubesCompletes() {
        stubSimpletubeCacheClearSimpletubes(Completable.complete())
        val testObserver = simpletubeDataRepository.clearSimpletubes().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearSimpletubesCallsCacheDataStore() {
        stubSimpletubeCacheClearSimpletubes(Completable.complete())
        simpletubeDataRepository.clearSimpletubes().test()
        verify(simpletubeCacheDataStore).clearSimpletubes()
    }

    @Test
    fun clearSimpletubesNeverCallsRemoteDataStore() {
        stubSimpletubeCacheClearSimpletubes(Completable.complete())
        simpletubeDataRepository.clearSimpletubes().test()
        verify(simpletubeRemoteDataStore, never()).clearSimpletubes()
    }
    
    @Test
    fun saveSimpletubesCompletes() {
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        val testObserver = simpletubeDataRepository.saveSimpletubes(
                SimpletubeFactory.makeSimpletubeList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveSimpletubesCallsCacheDataStore() {
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        simpletubeDataRepository.saveSimpletubes(SimpletubeFactory.makeSimpletubeList(2)).test()
        verify(simpletubeCacheDataStore).saveSimpletubes(any())
    }

    @Test
    fun saveSimpletubesNeverCallsRemoteDataStore() {
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        simpletubeDataRepository.saveSimpletubes(SimpletubeFactory.makeSimpletubeList(2)).test()
        verify(simpletubeRemoteDataStore, never()).saveSimpletubes(any())
    }
    
    @Test
    fun getSimpletubesCompletes() {
        stubSimpletubeDataStoreFactoryRetrieveDataStore(simpletubeCacheDataStore)
        stubSimpletubeCacheDataStoreGetSimpletubes(Single.just(
                SimpletubeFactory.makeSimpletubeEntityList(2)))
        val testObserver = simpletubeDataRepository.getSimpletubes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSimpletubesReturnsData() {
        stubSimpletubeDataStoreFactoryRetrieveDataStore(simpletubeCacheDataStore)
        val simpletubes = SimpletubeFactory.makeSimpletubeList(2)
        val simpletubeEntities = SimpletubeFactory.makeSimpletubeEntityList(2)
        simpletubes.forEachIndexed { index, simpletube ->
            stubSimpletubeMapperMapFromEntity(simpletubeEntities[index], simpletube) }
        stubSimpletubeCacheDataStoreGetSimpletubes(Single.just(simpletubeEntities))

        val testObserver = simpletubeDataRepository.getSimpletubes().test()
        testObserver.assertValue(simpletubes)
    }

    @Test
    fun getSimpletubesSavesSimpletubesWhenFromCacheDataStore() {
        stubSimpletubeDataStoreFactoryRetrieveDataStore(simpletubeCacheDataStore)
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        simpletubeDataRepository.saveSimpletubes(SimpletubeFactory.makeSimpletubeList(2)).test()
        verify(simpletubeCacheDataStore).saveSimpletubes(any())
    }

    @Test
    fun getSimpletubesNeverSavesSimpletubesWhenFromRemoteDataStore() {
        stubSimpletubeDataStoreFactoryRetrieveDataStore(simpletubeRemoteDataStore)
        stubSimpletubeCacheSaveSimpletubes(Completable.complete())
        simpletubeDataRepository.saveSimpletubes(SimpletubeFactory.makeSimpletubeList(2)).test()
        verify(simpletubeRemoteDataStore, never()).saveSimpletubes(any())
    }
    
    private fun stubSimpletubeCacheSaveSimpletubes(completable: Completable) {
        whenever(simpletubeCacheDataStore.saveSimpletubes(any()))
                .thenReturn(completable)
    }

    private fun stubSimpletubeCacheDataStoreGetSimpletubes(single: Single<List<SimpletubeEntity>>) {
        whenever(simpletubeCacheDataStore.getSimpletubes())
                .thenReturn(single)
    }

    private fun stubSimpletubeRemoteDataStoreGetSimpletubes(single: Single<List<SimpletubeEntity>>) {
        whenever(simpletubeRemoteDataStore.getSimpletubes())
                .thenReturn(single)
    }

    private fun stubSimpletubeCacheClearSimpletubes(completable: Completable) {
        whenever(simpletubeCacheDataStore.clearSimpletubes())
                .thenReturn(completable)
    }

    private fun stubSimpletubeDataStoreFactoryRetrieveCacheDataStore() {
        whenever(simpletubeDataStoreFactory.retrieveCacheDataStore())
                .thenReturn(simpletubeCacheDataStore)
    }

    private fun stubSimpletubeDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(simpletubeDataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(simpletubeCacheDataStore)
    }

    private fun stubSimpletubeDataStoreFactoryRetrieveDataStore(dataStore: SimpletubeDataStore) {
        whenever(simpletubeDataStoreFactory.retrieveDataStore())
                .thenReturn(dataStore)
    }

    private fun stubSimpletubeMapperMapFromEntity(
        simpletubeEntity: SimpletubeEntity,
                                                simpletube: Simpletube
    ) {
        whenever(simpletubeMapper.mapFromEntity(simpletubeEntity))
                .thenReturn(simpletube)
    }

}