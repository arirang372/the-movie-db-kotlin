package com.john.themoviedb.search.model

import android.os.Parcel
import android.os.Parcelable
import com.john.themoviedb.data.source.remote.model.BaseModel


data class Movie(
    var id: Long,
    var vote_average: String?,
    var poster_path: String?,
    var overview: String?,
    var title: String?,
    var release_date: String?,
    var backdrop_path: String?
) : Parcelable, BaseModel() {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(vote_average)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(title)
        parcel.writeString(release_date)
        parcel.writeString(backdrop_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

