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
    private val checkInternetStateUseCase: CheckInternetStateUseCase,
) : ViewModel() {


    private val liveData: MutableLiveData<Resource<DataModel>> = MutableLiveData()

    fun getAllByPage(page: Int): MutableLiveData<Resource<DataModel>> {
        viewModelScope.launch {
            call(liveData) {
                repo.getListOfAllModelsByPage(page)
            }
        }

        return liveData
    }

    private suspend fun <T> call(
        liveData: MutableLiveData<Resource<T>>,
        getResponse: suspend () -> Response<T>,
    ) {
        liveData.postValue(Resource.Loading)
        try {
            if (checkInternetStateUseCase.isInternetAvailable()) {
                liveData.postValue(handleResponse(getResponse()))
            } else {
                liveData.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> liveData.postValue(Resource.Error("Network failure"))
                else -> liveData.postValue(Resource.Error("Conversion Error: $t"))
            }
        }
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { currencyResponse ->
                return Resource.Success(currencyResponse)
            }
        }

        return Resource.Error(response.message())
    }


}


