package com.example.weathermvvm.di

import android.app.Application
import com.example.weathermvvm.MainApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<MainApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}