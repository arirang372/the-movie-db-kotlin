package com.john.themoviedb.details.model

import android.os.Parcel
import android.os.Parcelable


data class Trailer(
    var id: String?,
    var key: String?,
    var name: String?,
    var site: String?,
    var size: Int,
    var type: String?,
    var trailerImageUrl: String?,
    var trailerVideoUrl: String?
) : Parcelable, Comparable<Trailer> {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeInt(size)
        parcel.writeString(type)
        parcel.writeString(trailerImageUrl)
        parcel.writeString(trailerVideoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: Trailer): Int {
        return 0
    }
}