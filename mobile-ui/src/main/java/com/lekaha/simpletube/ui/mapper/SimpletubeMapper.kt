package com.lekaha.simpletube.ui.mapper

import com.lekaha.simpletube.presentation.model.SimpletubeView
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.model.SimpletubeViewModel.Companion.DISPLAY_TYPE_BROWSE
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem.Companion.toDisplayableItem
import io.reactivex.Observable

/**
 * Map a [SimpletubeView] to and from a [SimpletubeViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class SimpletubeMapper : Mapper<SimpletubeViewModel, SimpletubeView> {

    /**
     * Map a [SimpletubeView] instance to a [SimpletubeViewModel] instance
     */
    override fun mapToViewModel(type: SimpletubeView): SimpletubeViewModel {
        return SimpletubeViewModel(type.name, type.title, type.avatar)
    }

    @Throws(Exception::class)
    fun mapToViewModels(views: List<SimpletubeView>): List<DisplayableItem<*>> {
        return Observable.fromIterable(views)
            .map { mapToViewModel(it) }
            .map { wrapInDisplayableItem(it) }
            .toList()
            .blockingGet()
    }

    private fun wrapInDisplayableItem(viewEntity: SimpletubeViewModel): DisplayableItem<*> {
        return toDisplayableItem(viewEntity, DISPLAY_TYPE_BROWSE)
    }
}