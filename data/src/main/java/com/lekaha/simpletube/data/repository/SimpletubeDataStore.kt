package com.lekaha.simpletube.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.lekaha.simpletube.data.model.SimpletubeEntity

/**
 * Interface defining methods for the data operations related to Simpletubes.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface SimpletubeDataStore {

    fun clearSimpletubes(): Completable

    fun saveSimpletubes(simpletubes: List<SimpletubeEntity>): Completable

    fun getSimpletubes(): Single<List<SimpletubeEntity>>

}