package com.ukadovlad21.testtaskteamsdevit.data.repository

import com.ukadovlad21.testtaskteamsdevit.data.api.RetrofitInstance
import com.ukadovlad21.testtaskteamsdevit.models.DataModel
import com.ukadovlad21.testtaskteamsdevit.utils.Constant.Companion.APIKEY
import com.ukadovlad21.testtaskteamsdevit.utils.Constant.Companion.LANGUAGE
import retrofit2.Response

class MainRepository {

    suspend fun getListOfAllModelsByPage(page: Int): Response<DataModel> {
        return RetrofitInstance.api.getListOfModels(APIKEY, LANGUAGE, page)
    }


}