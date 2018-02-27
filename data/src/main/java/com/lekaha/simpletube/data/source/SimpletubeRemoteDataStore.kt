package com.lekaha.simpletube.data.source

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeDataStore
import com.lekaha.simpletube.data.repository.SimpletubeRemote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Implementation of the [SimpletubeDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class SimpletubeRemoteDataStore constructor(private val simpletubeRemote: SimpletubeRemote) :
    SimpletubeDataStore {

    override fun clearSimpletubes(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveSimpletubes(simpletubes: List<SimpletubeEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [SimpletubeEntity] instances from the API
     */
    override fun getSimpletubes(): Single<List<SimpletubeEntity>> {
        return simpletubeRemote.getVideos()
    }

}