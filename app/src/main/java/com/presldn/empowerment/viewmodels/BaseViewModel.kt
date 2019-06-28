package com.presldn.empowerment.viewmodels

import androidx.lifecycle.ViewModel
import com.presldn.empowerment.injection.component.DaggerViewModelInjector
import com.presldn.empowerment.injection.component.ViewModelInjector
import com.presldn.empowerment.module.NetworkModule

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .Builder()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is MainViewModel -> injector.inject(this)
        }
    }

}