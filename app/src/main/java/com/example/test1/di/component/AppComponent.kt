package com.example.test1.di.component

import com.example.test1.di.modules.AppModule
import com.example.test1.di.scope.ApplicationScope
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(dependencies = [BaseAppComponent::class], modules = [AppModule::class])
interface AppComponent {
    fun activityComponent(): ActivityComponent

}