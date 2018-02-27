package com.lekaha.simpletube.remote

import com.lekaha.simpletube.remote.model.SimpletubeModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Simpletube API
 */
interface SimpletubeService {

    @GET("playlist.json")
    fun getVideos(): Single<List<SimpletubeModel>>

}
