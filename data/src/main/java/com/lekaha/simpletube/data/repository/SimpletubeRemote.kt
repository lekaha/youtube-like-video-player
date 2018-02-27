package com.lekaha.simpletube.data.repository

import io.reactivex.Single
import com.lekaha.simpletube.data.model.SimpletubeEntity

/**
 * Interface defining methods for the caching of Simpletubes. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface SimpletubeRemote {

    /**
     * Retrieve a list of Simpletubes, from the cache
     */
    fun getVideos(): Single<List<SimpletubeEntity>>

}