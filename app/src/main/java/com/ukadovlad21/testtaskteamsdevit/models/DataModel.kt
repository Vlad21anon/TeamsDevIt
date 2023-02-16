package com.ukadovlad21.testtaskteamsdevit.models

data class DataModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)