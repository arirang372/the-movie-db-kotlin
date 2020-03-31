package com.john.themoviedb.data.source.remote.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T : Comparable<T>> : BaseApiResponse<T>() {
    @SerializedName("id")
    var id: Int = 0
}