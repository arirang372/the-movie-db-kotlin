package com.john.themoviedb.data.source.remote.model

import com.google.gson.annotations.SerializedName
import com.john.themoviedb.details.model.Review

class ApiResponse<T : BaseModel> : BaseApiResponse<T>() {
    @SerializedName("id")
    var id: Int = 0
}