package com.example.test1.di.component

import com.example.test1.di.modules.AppModule
import dagger.Component
import retrofit2.Retrofit

@Component(dependencies = [BaseAppComponent::class], modules = [AppModule::class])
interface AppComponent {
    fun activityComponent(): ActivityComponent

    fun retrofit(retrofit: Retrofit)
}