package com.lekaha.simpletube.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.lekaha.simpletube.data.model.SimpletubeEntity

/**
 * Interface defining methods for the caching of Simpletubes. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface SimpletubeCache {

    /**
     * Clear all Simpletubes from the cache
     */
    fun clearSimpletubes(): Completable

    /**
     * Save a given list of Simpletubes to the cache
     */
    fun saveSimpletubes(simpletubes: List<SimpletubeEntity>): Completable

    /**
     * Retrieve a list of Simpletubes, from the cache
     */
    fun getSimpletubes(): Single<List<SimpletubeEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}