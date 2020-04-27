package com.john.themoviedb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.john.themoviedb.search.model.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var mInstance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext, MovieDatabase::class.java,
                        "MovieDatabase.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return mInstance!!
        }
    }
}