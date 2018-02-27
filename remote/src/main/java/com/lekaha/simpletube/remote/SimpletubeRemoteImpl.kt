package com.lekaha.simpletube.remote

import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeRemote
import com.lekaha.simpletube.remote.mapper.SimpletubeEntityMapper
import io.reactivex.Single

/**
 * Remote implementation for retrieving Simpletube instances. This class implements the
 * [SimpletubeRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class SimpletubeRemoteImpl constructor(
    private val simpletubeService: SimpletubeService,
    private val entityMapper: SimpletubeEntityMapper
) :
    SimpletubeRemote {

    /**
     * Retrieve a list of [SimpletubeEntity] instances from the [SimpletubeService].
     */
    override fun getVideos(): Single<List<SimpletubeEntity>> {
        val videos = simpletubeService.getVideos()


//        return videos.map {
//            it.videoList.map { listItem -> entityMapper.mapToData(listItem) }
//        }

        return videos.map { it.map { listItem -> entityMapper.mapToData(listItem) } }
    }
}