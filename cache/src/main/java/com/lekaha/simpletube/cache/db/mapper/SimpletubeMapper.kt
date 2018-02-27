package com.lekaha.simpletube.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import com.lekaha.simpletube.cache.db.Db
import com.lekaha.simpletube.cache.model.CachedSimpletube

/**
 * Maps a [CachedSimpletube] instance to a database entity.
 */
class SimpletubeMapper : ModelDbMapper<CachedSimpletube> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedSimpletube]
     */
    override fun toContentValues(model: CachedSimpletube): ContentValues {
        val values = ContentValues()
        values.put(Db.SimpletubeTable.NAME, model.name)
        values.put(Db.SimpletubeTable.TITLE, model.title)
        values.put(Db.SimpletubeTable.AVATAR, model.avatar)
        return values
    }

    /**
     * Parse the cursor creating a [CachedSimpletube] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedSimpletube {
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.SimpletubeTable.NAME))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.SimpletubeTable.TITLE))
        val avatar = cursor.getString(cursor.getColumnIndexOrThrow(Db.SimpletubeTable.AVATAR))
        return CachedSimpletube(name, title, avatar)
    }

}