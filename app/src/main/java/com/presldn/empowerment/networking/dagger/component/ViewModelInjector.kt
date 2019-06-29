package com.presldn.empowerment.networking.dagger.component

import com.presldn.empowerment.networking.dagger.module.NetworkModule
import com.presldn.empowerment.viewmodels.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun Builder(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}