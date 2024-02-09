package com.example.test1.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test1.network.response.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun getMoviesDao() : MoviesDao

    companion object {
        const val DATABASE_NAME: String = "app_db"
    }
}