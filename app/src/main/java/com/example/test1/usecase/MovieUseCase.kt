package com.example.test1.usecase

import com.example.test1.network.request.Async
import com.example.test1.network.response.Movie
import com.example.test1.repo.MovieRepository
import com.example.test1.ui.viewholder.uimodel.MoviesDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val moviesRepository: MovieRepository) {

    private val _movies = MutableStateFlow<Async<List<MoviesDataModel>>>(Async.None)
    val movies: StateFlow<Async<List<MoviesDataModel>>> = _movies

    suspend fun getMovieStream(page: Int)  {
        withContext(Dispatchers.IO){
            moviesRepository.getMovieStream(page)
                .map {
                    when(it) {
                        is Async.Success -> processResult(it.data)
                        is Async.Error -> it
                        else -> Async.None
                    }
                }.collect {
                    _movies.value = it
                }
        }
    }

    private fun processResult(movies : List<Movie>?): Async<List<MoviesDataModel>> {
        val arrayListDataModel = arrayListOf<MoviesDataModel>()
        if (movies != null) {
            //moviesDao.insertMovies(movies)
            for (movie in  movies){
                arrayListDataModel.add(MoviesDataModel(movie.id, movie))
            }
        }
        return Async.Success(arrayListDataModel)
    }
}