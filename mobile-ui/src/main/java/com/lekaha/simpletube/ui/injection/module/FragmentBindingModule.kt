package com.lekaha.simpletube.ui.injection.module

import com.lekaha.simpletube.ui.browse.BrowseFragment
import com.lekaha.simpletube.ui.browse.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(
        modules = [
            BrowseActivityModule::class,
            BrowseModule::class])
    abstract fun provideBrowseFragment(): BrowseFragment

    @ContributesAndroidInjector(
        modules = [
            BrowseActivityModule::class,
            BrowseModule::class])
    abstract fun provideDetailFragment(): DetailFragment
}