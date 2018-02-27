package com.lekaha.simpletube.data.source

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeCache
import com.lekaha.simpletube.data.repository.SimpletubeDataStore
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Implementation of the [SimpletubeDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class SimpletubeCacheDataStore constructor(private val simpletubeCache: SimpletubeCache) :
    SimpletubeDataStore {

    /**
     * Clear all Simpletubes from the cache
     */
    override fun clearSimpletubes(): Completable {
        return simpletubeCache.clearSimpletubes()
    }

    /**
     * Save a given [List] of [SimpletubeEntity] instances to the cache
     */
    override fun saveSimpletubes(simpletubes: List<SimpletubeEntity>): Completable {
        return simpletubeCache.saveSimpletubes(simpletubes)
            .doOnComplete {
                simpletubeCache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    /**
     * Retrieve a list of [SimpletubeEntity] instance from the cache
     */
    override fun getSimpletubes(): Single<List<SimpletubeEntity>> {
        return simpletubeCache.getSimpletubes()
    }

}