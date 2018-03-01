package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.ui.BaseActivity
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.browse.BrowseActivity
import com.lekaha.simpletube.ui.browse.VideoFullscreenActivity
import com.lekaha.simpletube.ui.injection.qualifier.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityContext
    fun provideActivityContext(activity: BrowseActivity): Context = activity

    @Provides
    fun provideNavigator(baseActivity: BrowseActivity) = Navigator(baseActivity)
}