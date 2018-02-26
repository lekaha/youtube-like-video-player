package com.lekaha.simpletube.ui.injection.component

import android.app.Application
import com.lekaha.simpletube.ui.BufferooApplication
import com.lekaha.simpletube.ui.injection.module.ActivityBindingModule
import com.lekaha.simpletube.ui.injection.module.ApplicationModule
import com.lekaha.simpletube.ui.injection.module.NetModule
import com.lekaha.simpletube.ui.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(
        ActivityBindingModule::class,
        ApplicationModule::class,
        NetModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: BufferooApplication)

    override fun inject(instance: DaggerApplication)

}
