package com.lekaha.simpletube.data

import com.lekaha.simpletube.data.mapper.SimpletubeMapper
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.source.SimpletubeDataStoreFactory
import com.lekaha.simpletube.data.source.SimpletubeRemoteDataStore
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Provides an implementation of the [SimpletubeRepository] interface for communicating to and from
 * data sources
 */
class SimpletubeDataRepository constructor(
    private val factory: SimpletubeDataStoreFactory,
    private val simpletubeMapper: SimpletubeMapper
) :
    SimpletubeRepository {

    override fun clearSimpletubes(): Completable {
        return factory.retrieveCacheDataStore().clearSimpletubes()
    }

    override fun saveSimpletubes(simpletubes: List<Simpletube>): Completable {
        val simpletubeEntities = simpletubes.map { simpletubeMapper.mapToEntity(it) }
        return saveSimpletubeEntities(simpletubeEntities)
    }

    private fun saveSimpletubeEntities(simpletubes: List<SimpletubeEntity>): Completable {
        return factory.retrieveCacheDataStore().saveSimpletubes(simpletubes)
    }

    override fun getSimpletubes(): Single<List<Simpletube>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getSimpletubes()
            .flatMap {
                if (dataStore is SimpletubeRemoteDataStore) {
                    saveSimpletubeEntities(it).toSingle { it }
                } else {
                    Single.just(it)
                }
            }
            .map { list ->
                list.map { listItem ->
                    simpletubeMapper.mapFromEntity(listItem)
                }
            }
    }

}