package com.lekaha.simpletube.ui.injection.module

import com.lekaha.simpletube.ui.browse.BrowseAdapter
import com.lekaha.simpletube.ui.browse.BrowseDetailViewHolder
import com.lekaha.simpletube.ui.browse.BrowseViewHolder
import com.lekaha.simpletube.ui.model.SimpletubeSectionViewModel.Companion.DISPLAY_TYPE_BROWSE_DETAIL
import com.lekaha.simpletube.ui.model.SimpletubeViewModel.Companion.DISPLAY_TYPE_BROWSE
import com.lekaha.simpletube.ui.view.recycler.ItemComparator
import com.lekaha.simpletube.ui.view.recycler.ViewHolderBinder
import com.lekaha.simpletube.ui.view.recycler.ViewHolderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class BrowseModule {

    @Binds
    @IntoMap
    @IntKey(DISPLAY_TYPE_BROWSE)
    abstract fun provideBrowseViewHolderFactory(factory: BrowseViewHolder.BrowseViewHolderFactory)
            : ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(DISPLAY_TYPE_BROWSE)
    abstract fun provideBrowseViewHolderBinder(binder: BrowseViewHolder.BrowseViewHolderBinder)
            : ViewHolderBinder

    @Binds
    @IntoMap
    @IntKey(DISPLAY_TYPE_BROWSE_DETAIL)
    abstract fun provideBrowseDetailViewHolderFactory(factory: BrowseDetailViewHolder.BrowseDetailViewHolderFactory)
            : ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(DISPLAY_TYPE_BROWSE_DETAIL)
    abstract fun provideBrowseDetailViewHolderBinder(binder: BrowseDetailViewHolder.BrowseDetailViewHolderBinder)
            : ViewHolderBinder

    @Module
    companion object {

        @JvmStatic @Provides
        fun provideRecyclerAdapter(itemComparator: ItemComparator,
                                   factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
                                   binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder>)
                = BrowseAdapter(itemComparator, factoryMap, binderMap)

        @JvmStatic @Provides
        fun provideComparator(): ItemComparator = BrowseAdapter.BrowseItemComparator()
    }
}