package com.ukadovlad21.testtaskteamsdevit

import com.ukadovlad21.testtaskteamsdevit.models.DataModel
import com.ukadovlad21.testtaskteamsdevit.models.ItemDataModel

class ResponseMapper {


    fun map(
        responses: DataModel,
    ): List<ItemDataModel> {
        val list: MutableList<ItemDataModel> = mutableListOf()
        responses.results.forEach { item ->
            val listItem = ItemDataModel(
                item.name,
                item.original_name,
                item.poster_path,
                item.first_air_date,
                item.vote_average.toString(),
                item.vote_count.toString(),
                item.overview
            )
            list.add(listItem)
        }
        return list
    }
}