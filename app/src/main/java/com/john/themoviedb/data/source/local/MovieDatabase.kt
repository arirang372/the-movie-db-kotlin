package com.john.themoviedb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.search.model.Movie

@Database(
    entities = [Movie::class, Review::class, Trailer::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var mInstance: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                    context.applicationContext, MovieDatabase::class.java,
                    "MovieDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}