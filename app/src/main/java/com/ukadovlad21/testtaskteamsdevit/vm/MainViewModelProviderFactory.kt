package com.ukadovlad21.testtaskteamsdevit.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ukadovlad21.testtaskteamsdevit.data.repository.MainRepository
import com.ukadovlad21.testtaskteamsdevit.usecase.CheckInternetStateUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelProviderFactory(
    private val app: Application,
    private val currencyRepository: MainRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            currencyRepository,
            CheckInternetStateUseCase(app)
        ) as T
    }
}