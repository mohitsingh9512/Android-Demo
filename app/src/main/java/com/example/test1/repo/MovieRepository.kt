package com.example.test1.repo

import com.example.test1.extensions.InfoLanguage
import com.example.test1.network.api.MainApiInterface
import com.example.test1.network.request.Async
import com.example.test1.network.response.Movie
import com.example.test1.persistance.MoviesDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieRepository @AssistedInject constructor(
    private val mainApiInterface: MainApiInterface,
    private val moviesDao: MoviesDao,
    @Assisted private val infoLanguage: InfoLanguage
) {

    @AssistedFactory
    interface Factory {
        fun create(infoLanguage: InfoLanguage): MovieRepository

    }

    suspend fun getMovieStream(page: Int) = flow {
        val result = mainApiInterface.getPopularMovies("38a73d59546aa378980a88b645f487fc", infoLanguage.languageCode, page)
        kotlinx.coroutines.delay(4000)
        if(result.isSuccessful){
            result.body()?.results?.let {
                emit(Async.Success(it))
                saveToDB(it)
            } ?: run {
                emit(Async.Error(1))
            }
        }else {
            emit(Async.Error(0))
        }
    }

    suspend fun getMoviesFromDb()  = flow {
        emit(Async.Success(moviesDao.getMovies()))
    }

    private fun saveToDB(movies: List<Movie>) {
        CoroutineScope((SupervisorJob() + Dispatchers.IO)).apply {
            launch {
                moviesDao.insertMovies(movies)
            }
        }
    }
}