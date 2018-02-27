package com.lekaha.simpletube.cache

import com.lekaha.simpletube.cache.db.Db
import com.lekaha.simpletube.cache.db.DbOpenHelper
import com.lekaha.simpletube.cache.db.mapper.SimpletubeMapper
import com.lekaha.simpletube.cache.mapper.SimpletubeEntityMapper
import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.cache.test.factory.SimpletubeFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class SimpletubeCacheImplTest {

    private var entityMapper = SimpletubeEntityMapper()
    private var mapper = SimpletubeMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = SimpletubeCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.SimpletubeTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearSimpletubes().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveSimpletubesCompletes() {
        val simpletubeEntities = SimpletubeFactory.makeSimpletubeEntityList(2)

        val testObserver = databaseHelper.saveSimpletubes(simpletubeEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveSimpletubesSavesData() {
        val simpletubeCount = 2
        val simpletubeEntities = SimpletubeFactory.makeSimpletubeEntityList(simpletubeCount)

        databaseHelper.saveSimpletubes(simpletubeEntities).test()
        checkNumRowsInSimpletubesTable(simpletubeCount)
    }

    @Test
    fun getSimpletubesCompletes() {
        val testObserver = databaseHelper.getSimpletubes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSimpletubesReturnsData() {
        val simpletubeEntities = SimpletubeFactory.makeSimpletubeEntityList(2)
        val cachedSimpletubes = mutableListOf<CachedSimpletube>()
        simpletubeEntities.forEach {
            cachedSimpletubes.add(entityMapper.mapToCached(it))
        }
        insertSimpletubes(cachedSimpletubes)

        val testObserver = databaseHelper.getSimpletubes().test()
        testObserver.assertValue(simpletubeEntities)
    }

    private fun insertSimpletubes(cachedSimpletubes: List<CachedSimpletube>) {
        val database = databaseHelper.getDatabase()
        cachedSimpletubes.forEach {
            database.insertOrThrow(Db.SimpletubeTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInSimpletubesTable(expectedRows: Int) {
        val simpletubesCursor = databaseHelper.getDatabase().query(Db.SimpletubeTable.TABLE_NAME,
                null, null, null, null, null, null)
        simpletubesCursor.moveToFirst()
        val numberOfRows = simpletubesCursor.count
        simpletubesCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}