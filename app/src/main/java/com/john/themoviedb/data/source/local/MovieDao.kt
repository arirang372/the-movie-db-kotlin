package com.john.themoviedb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.john.themoviedb.search.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): MutableList<Movie>

    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovie(id: Long): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("DELETE FROM movie WHERE id = :id")
    fun deleteMovie(id: Long)
}