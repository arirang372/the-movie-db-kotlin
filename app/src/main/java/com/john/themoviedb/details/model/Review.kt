package com.john.themoviedb.details.model

import android.os.Parcel
import android.os.Parcelable

data class Review(
    var author: String?,
    var content: String?,
    var id: String?,
    var url: String?,
    var movieId: Long
) :
    Parcelable, Comparable<Review> {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeLong(movieId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: Review): Int {
        return 0
    }
}