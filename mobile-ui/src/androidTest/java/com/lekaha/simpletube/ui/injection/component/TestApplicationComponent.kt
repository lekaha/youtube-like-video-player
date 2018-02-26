package com.lekaha.simpletube.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.repository.BufferooRepository
import com.lekaha.simpletube.ui.injection.module.ActivityBindingModule
import com.lekaha.simpletube.ui.injection.module.TestApplicationModule
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import com.lekaha.simpletube.ui.test.TestApplication

@Component(modules = arrayOf(TestApplicationModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
@PerApplication
interface TestApplicationComponent : ApplicationComponent {

    fun bufferooRepository(): BufferooRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

}