package com.example.test1.usecase

import android.content.Context
import com.example.test1.di.scope.ApplicationScope
import com.example.test1.extensions.InfoLanguage
import com.example.test1.extensions.isNetworkAvailable
import com.example.test1.network.request.Async
import com.example.test1.repo.MovieRepository
import com.example.test1.ui.viewholder.uimodel.MoviesDataModel
import com.example.test1.ui.viewholder.uimodel.toUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val moviesRepository: MovieRepository.Factory,
    @ApplicationScope private val context: Context,
) {

    private val _movies = MutableStateFlow<Async<List<MoviesDataModel>>>(Async.None)
    val movies: StateFlow<Async<List<MoviesDataModel>>> = _movies

    suspend fun getMovieStream(page: Int)  {
        withContext(Dispatchers.IO){
            val repo = moviesRepository.create(InfoLanguage("en-US"))
            if(!context.isNetworkAvailable()){
                repo.getMoviesFromDb()
                    .map {
                        it.data.toUIModel()
                    }.collect {
                        _movies.value = it
                    }
                return@withContext
            }
            repo.getMovieStream(page)
                .map {
                    when(it) {
                        is Async.Success -> it.data.toUIModel()
                        is Async.Error -> it
                        else -> Async.None
                    }
                }.collect {
                    _movies.value = it
                }


        }
    }
}