package com.lekaha.simpletube.ui.injection.module

import android.app.Application
import android.content.Context
import com.lekaha.simpletube.cache.PreferencesHelper
import com.lekaha.simpletube.data.executor.JobExecutor
import com.lekaha.simpletube.data.repository.SimpletubeCache
import com.lekaha.simpletube.data.repository.SimpletubeRemote
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import com.lekaha.simpletube.remote.SimpletubeService
import com.lekaha.simpletube.ui.UiThread
import com.lekaha.simpletube.ui.injection.qualifier.ApplicationContext
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    @ApplicationContext
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper = mock()

    @Provides
    @PerApplication
    internal fun provideSimpletubeRepository(): SimpletubeRepository = mock()

    @Provides
    @PerApplication
    internal fun provideSimpletubeCache(): SimpletubeCache = mock()

    @Provides
    @PerApplication
    internal fun provideSimpletubeRemote(): SimpletubeRemote = mock()

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @PerApplication
    internal fun provideSimpletubeService(): SimpletubeService = mock()

    @Provides
    @PerApplication
    internal fun provideUiThread() = UiThread()

    @Provides
    @PerApplication
    internal fun provideJobExecutor() = JobExecutor()

}