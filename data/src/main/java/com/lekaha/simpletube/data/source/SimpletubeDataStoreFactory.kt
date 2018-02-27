package com.lekaha.simpletube.data.source

import com.lekaha.simpletube.data.repository.SimpletubeCache
import com.lekaha.simpletube.data.repository.SimpletubeDataStore

/**
 * Create an instance of a SimpletubeDataStore
 */
open class SimpletubeDataStoreFactory constructor(
    private val simpletubeCache: SimpletubeCache,
    private val simpletubeCacheDataStore: SimpletubeCacheDataStore,
    private val simpletubeRemoteDataStore: SimpletubeRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): SimpletubeDataStore {
        if (simpletubeCache.isCached() && !simpletubeCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): SimpletubeDataStore {
        return simpletubeCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): SimpletubeDataStore {
        return simpletubeRemoteDataStore
    }

}