package com.lekaha.simpletube.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object SimpletubeTable {
        const val TABLE_NAME = "simpletubes"

        const val SIMPLETUBE_ID = "simpletube_id"
        const val NAME = "id"
        const val TITLE = "title"
        const val THUMBNAIL = "thumbnail"
        const val DESCRIPTION = "description"
        const val VIDEO_URL = "video_url"
        const val VIDEO_DURATION = "video_duration"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        SIMPLETUBE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        NAME + " TEXT NOT NULL," +
                        TITLE + " TEXT," +
                        THUMBNAIL + " TEXT" +
                        DESCRIPTION + " TEXT" +
                        VIDEO_URL + " TEXT" +
                        VIDEO_DURATION + " INTEGER" +
                        "); "
    }

}