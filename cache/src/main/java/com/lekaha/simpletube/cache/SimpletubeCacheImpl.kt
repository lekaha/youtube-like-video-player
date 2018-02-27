package com.lekaha.simpletube.cache

import android.database.sqlite.SQLiteDatabase
import com.lekaha.simpletube.cache.db.Db
import com.lekaha.simpletube.cache.db.DbOpenHelper
import com.lekaha.simpletube.cache.db.constants.SimpletubeConstants
import com.lekaha.simpletube.cache.db.mapper.SimpletubeMapper
import com.lekaha.simpletube.cache.mapper.SimpletubeEntityMapper
import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.data.model.SimpletubeEntity
import com.lekaha.simpletube.data.repository.SimpletubeCache
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Cached implementation for retrieving and saving Simpletube instances. This class implements the
 * [SimpletubeCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class SimpletubeCacheImpl constructor(
    dbOpenHelper: DbOpenHelper,
    private val entityMapper: SimpletubeEntityMapper,
    private val mapper: SimpletubeMapper,
    private val preferencesHelper: PreferencesHelper
) :
    SimpletubeCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearSimpletubes(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.SimpletubeTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Save the given list of [SimpletubeEntity] instances to the database.
     */
    override fun saveSimpletubes(simpletubes: List<SimpletubeEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                simpletubes.forEach {
                    saveSimpletube(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [SimpletubeEntity] instances from the database.
     */
    override fun getSimpletubes(): Single<List<SimpletubeEntity>> {
        return Single.defer<List<SimpletubeEntity>> {
            val updatesCursor = database.rawQuery(SimpletubeConstants.QUERY_GET_ALL_SIMPLETUBES, null)
            val simpletubes = mutableListOf<SimpletubeEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedSimpletube = mapper.parseCursor(updatesCursor)
                simpletubes.add(entityMapper.mapFromCached(cachedSimpletube))
            }

            updatesCursor.close()
            Single.just<List<SimpletubeEntity>>(simpletubes)
        }
    }

    /**
     * Helper method for saving a [CachedSimpletube] instance to the database.
     */
    private fun saveSimpletube(cachedSimpletube: CachedSimpletube) {
        database.insert(Db.SimpletubeTable.TABLE_NAME, null, mapper.toContentValues(cachedSimpletube))
    }

    /**
     * Checked whether there are instances of [CachedSimpletube] stored in the cache
     */
    override fun isCached(): Boolean {
        return database.rawQuery(SimpletubeConstants.QUERY_GET_ALL_SIMPLETUBES, null).count > 0
    }

    /**
     * Set a point in time at when the cache was last updated
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}