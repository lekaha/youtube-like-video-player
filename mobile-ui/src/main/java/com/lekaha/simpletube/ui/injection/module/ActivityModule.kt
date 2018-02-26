package com.lekaha.simpletube.ui.injection.module

import com.lekaha.simpletube.ui.BaseActivity
import com.lekaha.simpletube.ui.Navigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideNavigator(baseActivity: BaseActivity) = Navigator(baseActivity)
}