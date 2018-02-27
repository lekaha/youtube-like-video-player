package com.lekaha.simpletube.cache.db.constants

import com.lekaha.simpletube.cache.db.Db

/**
 * Defines DB queries for the Simpletube Table
 */
object SimpletubeConstants {

    internal val QUERY_GET_ALL_SIMPLETUBES = "SELECT * FROM " + Db.SimpletubeTable.TABLE_NAME

}