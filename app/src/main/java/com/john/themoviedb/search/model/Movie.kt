package com.john.themoviedb.search.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "vote_average")
    var vote_average: String?,
    @ColumnInfo(name = "rating")
    var rating: Float,
    @ColumnInfo(name = "poster_path")
    var poster_path: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "release_date")
    var release_date: String?,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?
) : Parcelable, Comparable<Movie> {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(vote_average)
        parcel.writeFloat(rating)
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

    override fun compareTo(other: Movie): Int {
        return 0
    }
}

