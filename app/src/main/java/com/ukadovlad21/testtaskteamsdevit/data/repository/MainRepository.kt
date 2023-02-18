package com.ukadovlad21.testtaskteamsdevit.data.repository

import androidx.lifecycle.MutableLiveData
import com.ukadovlad21.testtaskteamsdevit.data.api.RetrofitInstance
import com.ukadovlad21.testtaskteamsdevit.models.DataModel
import com.ukadovlad21.testtaskteamsdevit.usecase.CheckInternetStateUseCase
import com.ukadovlad21.testtaskteamsdevit.utils.Constant.Companion.APIKEY
import com.ukadovlad21.testtaskteamsdevit.utils.Constant.Companion.LANGUAGE
import com.ukadovlad21.testtaskteamsdevit.utils.Resource
import retrofit2.Response
import java.io.IOException

class MainRepository(
    private val checkInternetStateUseCase: CheckInternetStateUseCase
) {

    suspend fun getListOfAllModelsByPage(page: Int): Response<DataModel> {
        return RetrofitInstance.api.getListOfModels(APIKEY, LANGUAGE, page)
    }


    suspend fun <T> call(
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
            liveData.postValue(Resource.Error("Some error occurred"))
        }
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { currencyResponse ->
                return Resource.Success(currencyResponse)
            }
        }

        return Resource.Error("Server is not responding")
    }

}