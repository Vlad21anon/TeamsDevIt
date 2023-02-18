package com.ukadovlad21.testtaskteamsdevit.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ukadovlad21.testtaskteamsdevit.data.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelProviderFactory(
    private val currencyRepository: MainRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            currencyRepository,
        ) as T
    }
}