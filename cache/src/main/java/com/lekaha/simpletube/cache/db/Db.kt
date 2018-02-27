package com.lekaha.simpletube.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object SimpletubeTable {
        const val TABLE_NAME = "simpletubes"

        const val SIMPLETUBE_ID = "simpletube_id"
        const val NAME = "name"
        const val TITLE = "title"
        const val AVATAR = "avatar"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        SIMPLETUBE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        NAME + " TEXT NOT NULL," +
                        TITLE + " TEXT," +
                        AVATAR + " TEXT" +
                        "); "
    }

}