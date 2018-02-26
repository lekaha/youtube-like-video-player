package com.lekaha.simpletube.ui.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import com.lekaha.simpletube.cache.PreferencesHelper
import com.lekaha.simpletube.data.executor.JobExecutor
import com.lekaha.simpletube.data.repository.BufferooCache
import com.lekaha.simpletube.data.repository.BufferooRemote
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.repository.BufferooRepository
import com.lekaha.simpletube.remote.BufferooService
import com.lekaha.simpletube.ui.UiThread
import com.lekaha.simpletube.ui.injection.scopes.PerApplication

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRepository(): BufferooRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(): BufferooCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(): BufferooRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideBufferooService(): BufferooService {
        return mock()
    }

}