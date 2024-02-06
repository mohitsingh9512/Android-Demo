package com.example.test1

import android.app.Application
import com.example.test1.di.component.AppComponent
import com.example.test1.di.component.BaseAppComponent
import com.example.test1.di.component.DaggerAppComponent
import com.example.test1.di.component.DaggerBaseAppComponent
import com.example.test1.di.factory.BaseModule
import com.example.test1.di.modules.AppModule

class MyApplication : Application() {

    private val baseAppComponent: BaseAppComponent by lazy {
        DaggerBaseAppComponent.builder().build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .baseAppComponent(baseAppComponent)
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}