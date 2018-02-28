package com.lekaha.simpletube.data

import com.lekaha.simpletube.data.mapper.SimpletubeMapper
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.source.SimpletubeDataStoreFactory
import com.lekaha.simpletube.data.source.SimpletubeRemoteDataStore
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSection
import com.lekaha.simpletube.domain.model.SimpletubeSections
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
    override fun getSimpletube(title: String?): Single<Simpletube> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getSimpletubes().flatMap {
            if (dataStore is SimpletubeRemoteDataStore) {
                saveSimpletubeEntities(it)
            }

            val entities = it.filter { it.title == title }
            if (entities.isNotEmpty()) {
                Single.just(simpletubeMapper.mapFromEntity(entities.first()))
            } else {
                Single.never<Simpletube>()
            }
        }
    }

    override fun getSimpletubeSections(simpletube: Simpletube): Single<SimpletubeSections> =
        Single.just(SimpletubeSections(simpletube,
            arrayListOf(
                SimpletubeSection("0", "Section 1", "Section 1 description"),
                SimpletubeSection("1", "Section 2", "Section 2 description"),
                SimpletubeSection("2", "Section 3", "Section 3 description"),
                SimpletubeSection("3", "Section 4", "Section 4 description"),
                SimpletubeSection("4", "Section 5", "Section 5 description"),
                SimpletubeSection("5", "Section 6", "Section 6 description"),
                SimpletubeSection("6", "Section 7", "Section 7 description"),
                SimpletubeSection("7", "Section 8", "Section 8 description"),
                SimpletubeSection("8", "Section 9", "Section 9 description")
            )
        ))

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