package com.lekaha.simpletube.ui.model

/**
 * Representation for a [SimpletubeViewModel] fetched from an external layer data source
 */
class SimpletubeViewModel(val name: String, val title: String, val avatar: String) {
    companion object {
        const val DISPLAY_TYPE_BROWSE: Int = 3
    }
}