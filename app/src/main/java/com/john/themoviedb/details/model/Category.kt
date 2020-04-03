package com.john.themoviedb.details.model

import android.os.Parcel
import android.os.Parcelable


data class Category(var categoryType: String?) : Parcelable, Comparable<Category> {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(categoryType)
    }

    override fun compareTo(other: Category): Int {
        return 0
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}



