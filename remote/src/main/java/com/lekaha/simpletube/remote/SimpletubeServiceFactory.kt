package com.lekaha.simpletube.remote

import com.google.gson.Gson
import com.lekaha.simpletube.remote.service.HttpServiceFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provide "make" methods to create instances of [SimpletubeService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object SimpletubeServiceFactory : HttpServiceFactory<SimpletubeService>() {

    override fun makeService(isDebug: Boolean,
        baseUrl: String,
        connectTimeout: Long,
        readTimeout: Long,
        interceptors: Array<Interceptor>): SimpletubeService {

        val okHttpClient = makeOkHttpClient(isDebug, connectTimeout, readTimeout, interceptors)
        return makeSimpletubeService(baseUrl, okHttpClient, makeGson())
    }

    private fun makeSimpletubeService(baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson): SimpletubeService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(SimpletubeService::class.java)
    }
}