package com.ukadovlad21.testtaskteamsdevit.utils

sealed class Resource<out DATA> {

    class Success<DATA>(val data: DATA) : Resource<DATA>()

    object Error : Resource<Nothing>()

    object Loading : Resource<Nothing>()

}