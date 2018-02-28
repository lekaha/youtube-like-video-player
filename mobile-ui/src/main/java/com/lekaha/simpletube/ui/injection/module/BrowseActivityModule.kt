package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.cache.PreferencesHelper
import com.lekaha.simpletube.cache.SimpletubeCacheImpl
import com.lekaha.simpletube.cache.db.DbOpenHelper
import com.lekaha.simpletube.cache.mapper.SimpletubeEntityMapper
import com.lekaha.simpletube.data.SimpletubeDataRepository
import com.lekaha.simpletube.data.repository.SimpletubeCache
import com.lekaha.simpletube.data.repository.SimpletubeRemote
import com.lekaha.simpletube.data.source.SimpletubeCacheDataStore
import com.lekaha.simpletube.data.source.SimpletubeDataStoreFactory
import com.lekaha.simpletube.data.source.SimpletubeRemoteDataStore
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.interactor.browse.GetSimpletube
import com.lekaha.simpletube.domain.interactor.browse.GetSimpletubeSections
import com.lekaha.simpletube.domain.interactor.browse.GetSimpletubes
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import com.lekaha.simpletube.presentation.browse.BrowseDetailSimpletubesContract
import com.lekaha.simpletube.presentation.browse.BrowseDetailSimpletubesPresenter
import com.lekaha.simpletube.presentation.browse.BrowseSimpletubesContract
import com.lekaha.simpletube.presentation.browse.BrowseSimpletubesPresenter
import com.lekaha.simpletube.presentation.mapper.SimpletubeMapper
import com.lekaha.simpletube.presentation.mapper.SimpletubeSectionMapper
import com.lekaha.simpletube.presentation.mapper.SimpletubeSectionsMapper
import com.lekaha.simpletube.remote.SimpletubeRemoteImpl
import com.lekaha.simpletube.remote.SimpletubeService
import com.lekaha.simpletube.ui.browse.BrowseDetailViewHolder
import com.lekaha.simpletube.ui.browse.BrowseViewHolder
import com.lekaha.simpletube.ui.injection.qualifier.ActivityContext
import com.lekaha.simpletube.ui.model.BrowseDetailViewModelFactory
import com.lekaha.simpletube.ui.model.BrowseViewModelFactory
import dagger.Module
import dagger.Provides


/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @Provides
    internal fun provideGetSimpletubes(
        simpletubeRepository: SimpletubeRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ) = GetSimpletubes(simpletubeRepository, threadExecutor, postExecutionThread)

    @Provides
    internal fun provideGetSimletubeSections(
        simpletubeRepository: SimpletubeRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ) = GetSimpletubeSections(simpletubeRepository, threadExecutor, postExecutionThread)

    @Provides
    internal fun provideGetSimpletube(
        simpletubeRepository: SimpletubeRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ) = GetSimpletube(simpletubeRepository, threadExecutor, postExecutionThread)

    @Provides
    internal fun provideSimpletubeEntityMapper() = SimpletubeEntityMapper()

    @Provides
    internal fun provideSimpletubeMapper() =
        com.lekaha.simpletube.presentation.mapper.SimpletubeMapper()

    @Provides
    internal fun provideSimpletubeSectionMapper() = SimpletubeSectionMapper()

    @Provides
    internal fun provideSimpletubeSectionsMapper() = SimpletubeSectionsMapper()

    @Provides
    internal fun provideDbSimpletubeMapper() =
        com.lekaha.simpletube.cache.db.mapper.SimpletubeMapper()

    @Provides
    internal fun provideRemoteSimpletubeMapper() =
        com.lekaha.simpletube.remote.mapper.SimpletubeEntityMapper()

    @Provides
    internal fun provideDataSimpletubeMapper() =
        com.lekaha.simpletube.data.mapper.SimpletubeMapper()

    @Provides
    internal fun provideUiSimpletubeSectionMapper() =
        com.lekaha.simpletube.ui.mapper.SimpletubeSectionMapper()

    @Provides
    internal fun provideUiSimpletubeMapper() = com.lekaha.simpletube.ui.mapper.SimpletubeMapper()

    @Provides
    internal fun provideSimpletubeCache(
        factory: DbOpenHelper,
        entityMapper: SimpletubeEntityMapper,
        mapper: com.lekaha.simpletube.cache.db.mapper.SimpletubeMapper,
        helper: PreferencesHelper
    ): SimpletubeCache = SimpletubeCacheImpl(factory, entityMapper, mapper, helper)

    @Provides
    internal fun provideSimpletubeRemote(
        service: SimpletubeService,
        factory: com.lekaha.simpletube.remote.mapper.SimpletubeEntityMapper
    ): SimpletubeRemote = SimpletubeRemoteImpl(service, factory)

    @Provides
    internal fun provideSimpletubeDataStoreFactory(
        simpletubeCache: SimpletubeCache,
        simpletubeCacheDataStore: SimpletubeCacheDataStore,
        simpletubeRemoteDataStore: SimpletubeRemoteDataStore
    ): SimpletubeDataStoreFactory = SimpletubeDataStoreFactory(
        simpletubeCache,
        simpletubeCacheDataStore,
        simpletubeRemoteDataStore
    )

    @Provides
    internal fun provideSimpletubeCacheDataStore(simpletubeCache: SimpletubeCache)
            : SimpletubeCacheDataStore = SimpletubeCacheDataStore(simpletubeCache)

    @Provides
    internal fun provideSimpletubeRemoteDataStore(simpletubeRemote: SimpletubeRemote)
            : SimpletubeRemoteDataStore = SimpletubeRemoteDataStore(simpletubeRemote)

    @Provides
    internal fun provideSimpletubeRepository(
        factory: SimpletubeDataStoreFactory,
        mapper: com.lekaha.simpletube.data.mapper.SimpletubeMapper
    ): SimpletubeRepository = SimpletubeDataRepository(factory, mapper)

    @Provides
    internal fun provideBrowseViewHolderFactory(@ActivityContext context: Context) =
        BrowseViewHolder.BrowseViewHolderFactory(context)

    @Provides
    internal fun provideBrowseViewHolderBinder() = BrowseViewHolder.BrowseViewHolderBinder()

    @Provides
    internal fun provideBrowseDetailViewHolderFactory(@ActivityContext context: Context) =
        BrowseDetailViewHolder.BrowseDetailViewHolderFactory(context)

    @Provides
    internal fun provideBrowseDetailViewHolderBinder() =
        BrowseDetailViewHolder.BrowseDetailViewHolderBinder()

    @Provides
    internal fun provideBrowsePresenter(
        getSimpletubes: GetSimpletubes,
        mapper: SimpletubeMapper
    ): BrowseSimpletubesContract.Presenter =
        BrowseSimpletubesPresenter(getSimpletubes, mapper)

    @Provides
    internal fun provideBrowseDetailPresenter(
        getSimpletubeSections: GetSimpletubeSections,
        getSimpletube: GetSimpletube,
        mapper: SimpletubeSectionsMapper
    ): BrowseDetailSimpletubesContract.Presenter =
        BrowseDetailSimpletubesPresenter(getSimpletubeSections, getSimpletube, mapper)

    @Provides
    internal fun provideBrowseViewModelFactory(presenter: BrowseSimpletubesContract.Presenter) =
        BrowseViewModelFactory(presenter)

    @Provides
    internal fun provideBrowseDetailViewModelFactory(presenter: BrowseDetailSimpletubesContract.Presenter) =
        BrowseDetailViewModelFactory(presenter)
}
