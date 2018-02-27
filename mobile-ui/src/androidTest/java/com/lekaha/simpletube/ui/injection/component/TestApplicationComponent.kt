package com.lekaha.simpletube.ui.injection.component

import android.app.Application
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import com.lekaha.simpletube.ui.injection.module.TestActivityBindingModule
import com.lekaha.simpletube.ui.injection.module.TestApplicationModule
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import com.lekaha.simpletube.ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [TestApplicationModule::class, TestActivityBindingModule::class,
        AndroidSupportInjectionModule::class]
)
@PerApplication
interface TestApplicationComponent : ApplicationComponent {

    fun simpletubeRepository(): SimpletubeRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

}