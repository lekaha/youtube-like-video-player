package com.lekaha.simpletube.domain.repository

import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSections
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface SimpletubeRepository {

    fun clearSimpletubes(): Completable

    fun saveSimpletubes(simpletubes: List<Simpletube>): Completable

    fun getSimpletubes(): Single<List<Simpletube>>

    fun getSimpletube(title: String?): Single<Simpletube>

    fun getSimpletubeSections(simpletube: Simpletube): Single<SimpletubeSections>
}