package com.lekaha.simpletube.remote

import com.lekaha.simpletube.data.model.BufferooEntity
import com.lekaha.simpletube.data.repository.BufferooRemote
import com.lekaha.simpletube.remote.mapper.BufferooEntityMapper

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooRemoteImpl constructor(
    private val bufferooService: BufferooService,
    private val entityMapper: BufferooEntityMapper
) :
    BufferooRemote {

    /**
     * Retrieve a list of [BufferooEntity] instances from the [BufferooService].
     */
    override fun getBufferoos() = bufferooService.getBufferoos()
        .map {
            it.team.map { listItem -> entityMapper.mapToData(listItem) }
        }

}