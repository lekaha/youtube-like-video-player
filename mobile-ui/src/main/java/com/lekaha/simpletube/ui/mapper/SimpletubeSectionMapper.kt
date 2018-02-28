package com.lekaha.simpletube.ui.mapper

import com.lekaha.simpletube.presentation.model.SimpletubeSectionView
import com.lekaha.simpletube.presentation.model.SimpletubeView
import com.lekaha.simpletube.ui.model.SimpletubeSectionViewModel
import com.lekaha.simpletube.ui.model.SimpletubeSectionViewModel.Companion.DISPLAY_TYPE_BROWSE_DETAIL
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem.Companion.toDisplayableItem
import io.reactivex.Observable

/**
 * Map a [SimpletubeView] to and from a [SimpletubeViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class SimpletubeSectionMapper : Mapper<SimpletubeSectionViewModel, SimpletubeSectionView> {

    /**
     * Map a [SimpletubeView] instance to a [SimpletubeViewModel] instance
     */
    override fun mapToViewModel(type: SimpletubeSectionView): SimpletubeSectionViewModel {
        return SimpletubeSectionViewModel(type.title, type.description)
    }

    @Throws(Exception::class)
    fun mapToViewModels(
        views: List<SimpletubeSectionView>,
        callback: (SimpletubeSectionViewModel) -> Unit
    ): List<DisplayableItem<*>> {

        return Observable.fromIterable(views)
            .map {
                val vm = mapToViewModel(it)
                vm.onClickListener = callback

                vm
            }
            .map { wrapInDisplayableItem(it) }
            .toList()
            .blockingGet()
    }

    private fun wrapInDisplayableItem(viewEntity: SimpletubeSectionViewModel): DisplayableItem<*> {
        return toDisplayableItem(viewEntity, DISPLAY_TYPE_BROWSE_DETAIL)
    }
}