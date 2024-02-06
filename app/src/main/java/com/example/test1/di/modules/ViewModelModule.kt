package com.example.test1.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test1.viewmodel.MainViewModel
import com.example.test1.di.factory.ViewModelProviderFactory
import com.example.test1.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(modelProvider: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap()
    @ViewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(mainViewModel: MainViewModel) :  ViewModel

}
