package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.OfflineRequestMockInterceptor
import com.lekaha.simpletube.OfflineResponseMockInterceptor
import com.lekaha.simpletube.remote.SimpletubeServiceFactory
import com.lekaha.simpletube.ui.BuildConfig
import com.lekaha.simpletube.ui.injection.qualifier.ApplicationContext
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class NetModule {

    companion object {
        const val BASE_URL = "BASE_URL"
        const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
        const val READ_TIMEOUT = "READ_TIMEOUT"
    }


    @Provides
    @PerApplication
    internal fun provideSimpletubeService(
        @Named(BASE_URL) baseUrl: String,
        @Named(CONNECT_TIMEOUT) connectTimeout: Long,
        @Named(READ_TIMEOUT) readTimeout: Long,
        offlineResponseInterceptor: OfflineResponseMockInterceptor
    ) = SimpletubeServiceFactory.makeService(
        BuildConfig.DEBUG,
        baseUrl,
        connectTimeout,
        readTimeout,
        arrayOf(offlineResponseInterceptor)
    )

    @Provides
    @PerApplication
    internal fun provideOfflineResponseInterceptor(@ApplicationContext context: Context) =
        OfflineResponseMockInterceptor(context)

    @Provides
    @PerApplication
    internal fun provideOfflineRequestInterceptor(@ApplicationContext context: Context) =
        OfflineRequestMockInterceptor(context)

    @Provides
    @Named(BASE_URL)
    internal fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Named(CONNECT_TIMEOUT)
    internal fun provideConnectTimeout() = 120L

    @Provides
    @Named(READ_TIMEOUT)
    internal fun provideReadTimeout() = 120L
}