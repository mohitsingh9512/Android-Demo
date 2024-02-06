package com.example.test1.di.component

import com.example.test1.di.factory.BaseModule
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [BaseModule::class])
interface BaseAppComponent {

    fun getRetrofit() : Retrofit

}