package com.example.test1.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test1.network.response.Movie
import javax.inject.Singleton

@Singleton
@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies : List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie :Movie)

    @Query("Select * from movie")
    suspend fun getMovies() : List<Movie>

    @Query("Delete FROM movie")
    suspend fun clearAll()
}