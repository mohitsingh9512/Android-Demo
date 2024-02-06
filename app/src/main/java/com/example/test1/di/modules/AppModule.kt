package com.example.test1.di.modules

import android.app.Application
import android.content.Context
import com.example.test1.di.scope.ApplicationScope
import com.example.test1.network.api.MainApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@DisableInstallInCheck
class AppModule(var application: Application) {

    @Provides
    @ApplicationScope
    fun providesApplication() : Application {
        return application
    }

    @Provides
    @ApplicationScope
    fun providesApplicationContext() : Context {
        return application.applicationContext
    }

    @Provides
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }

}
