package com.lekaha.simpletube.domain.model

/**
 * Representation for a [SimpletubeSections] fetched from an external layer data source
 */
data class SimpletubeSections(val simpletube: Simpletube, val sections: List<SimpletubeSection>)