package com.lekaha.simpletube.cache.db.mapper

import android.database.Cursor
import com.lekaha.simpletube.cache.BuildConfig
import com.lekaha.simpletube.cache.db.Db
import com.lekaha.simpletube.cache.db.DbOpenHelper
import com.lekaha.simpletube.cache.model.CachedSimpletube
import com.lekaha.simpletube.cache.test.DefaultConfig
import com.lekaha.simpletube.cache.test.factory.SimpletubeFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class SimpletubeMapperTest {

    private lateinit var simpletubeMapper: SimpletubeMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        simpletubeMapper = SimpletubeMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedSimpletube = SimpletubeFactory.makeCachedSimpletube()
        insertCachedSimpletube(cachedSimpletube)

        val cursor = retrieveCachedSimpletubeCursor()
        assertEquals(cachedSimpletube, simpletubeMapper.parseCursor(cursor))
    }

    private fun retrieveCachedSimpletubeCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.SimpletubeTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedSimpletube(cachedSimpletube: CachedSimpletube) {
        database.insertOrThrow(
            Db.SimpletubeTable.TABLE_NAME, null,
            simpletubeMapper.toContentValues(cachedSimpletube)
        )
    }

}