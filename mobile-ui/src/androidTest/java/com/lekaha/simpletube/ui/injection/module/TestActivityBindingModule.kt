package com.lekaha.simpletube.ui.injection.module

import android.content.Context
import com.lekaha.simpletube.ui.BaseActivity
import com.lekaha.simpletube.ui.browse.BrowseActivity
import com.lekaha.simpletube.ui.injection.qualifier.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [ActivityModule::class]
    )
    abstract fun bindMainActivity(): BrowseActivity

    @Binds
    abstract fun bindMainActivity(activity: BrowseActivity): BaseActivity

    @Binds
    @ActivityContext
    abstract fun provideActivityContext(activity: BaseActivity): Context
}