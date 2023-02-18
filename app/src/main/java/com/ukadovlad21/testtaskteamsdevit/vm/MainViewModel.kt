package com.ukadovlad21.testtaskteamsdevit.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukadovlad21.testtaskteamsdevit.data.repository.MainRepository
import com.ukadovlad21.testtaskteamsdevit.models.DataModel
import com.ukadovlad21.testtaskteamsdevit.usecase.CheckInternetStateUseCase
import com.ukadovlad21.testtaskteamsdevit.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    private val repo: MainRepository,
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<DataModel>> = MutableLiveData()

    fun getAllByPage(page: Int): MutableLiveData<Resource<DataModel>> {
        viewModelScope.launch {
            repo.call(liveData) {
                repo.getListOfAllModelsByPage(page)
            }
        }

        return liveData
    }

}


