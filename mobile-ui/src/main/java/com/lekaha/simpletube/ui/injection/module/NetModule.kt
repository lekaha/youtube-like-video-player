package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.lekaha.simpletube.remote.SimpletubeServiceFactory
import com.lekaha.simpletube.ui.BuildConfig
import com.lekaha.simpletube.ui.injection.qualifier.ApplicationContext
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import com.readystatesoftware.chuck.ChuckInterceptor
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
        chuckInterceptor: ChuckInterceptor,
        stethoInterceptor: StethoInterceptor
    ) = SimpletubeServiceFactory.makeService(
        BuildConfig.DEBUG,
        baseUrl,
        connectTimeout,
        readTimeout,
        arrayOf(chuckInterceptor, stethoInterceptor))

    @Provides
    @PerApplication
    internal fun provideChuckInterceptor(@ApplicationContext context: Context)
            = ChuckInterceptor(context)

    @Provides
    internal fun provideStethoInterceptor() = StethoInterceptor()

    @Provides
    @Named(BASE_URL)
    internal fun provideBaseUrl() = "${BuildConfig.BASE_URL}"

    @Provides
    @Named(CONNECT_TIMEOUT)
    internal fun provideConnectTimeout() = 120L

    @Provides
    @Named(READ_TIMEOUT)
    internal fun provideReadTimeout() = 120L
}