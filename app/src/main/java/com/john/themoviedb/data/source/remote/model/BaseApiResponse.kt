package com.john.themoviedb.data.source.remote.model

import com.google.gson.annotations.SerializedName

open class BaseApiResponse<T : Comparable<T>> {
    @SerializedName("results")
    lateinit var results: MutableList<T>

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("page")
    var page: Int = 0
}