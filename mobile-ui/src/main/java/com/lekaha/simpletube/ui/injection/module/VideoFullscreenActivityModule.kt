package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.browse.VideoFullscreenActivity
import com.lekaha.simpletube.ui.injection.qualifier.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class VideoFullscreenActivityModule {

    @Provides
    @ActivityContext
    fun provideActivityContext(activity: VideoFullscreenActivity): Context = activity

    @Provides
    fun provideNavigator(baseActivity: VideoFullscreenActivity) = Navigator(baseActivity)
}