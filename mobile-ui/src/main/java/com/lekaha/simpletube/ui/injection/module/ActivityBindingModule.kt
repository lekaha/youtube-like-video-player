package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.ui.browse.BrowseActivity
import com.lekaha.simpletube.ui.browse.VideoFullscreenActivity
import com.lekaha.simpletube.ui.injection.qualifier.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [MainActivityModule::class,
            FragmentBindingModule::class]
    )
    abstract fun bindMainActivity(): BrowseActivity

//    @Binds
//    abstract fun bindMainActivity(activity: BrowseActivity): BrowseActivity

    @ContributesAndroidInjector(
        modules = [VideoFullscreenActivityModule::class]
    )
    abstract fun bindVideoFullscreenActivity(): VideoFullscreenActivity

//    @Binds
//    abstract fun bindVideoFullscreenActivity(activity: VideoFullscreenActivity): BaseActivity

//    @Binds
//    @ActivityContext
//    abstract fun provideActivityContext(activity: BrowseActivity): Context
//
//    @Binds
//    @ActivityContext
//    abstract fun provideActivityContext(activity: VideoFullscreenActivity): Context

}