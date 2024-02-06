package com.example.test1.repo

import android.graphics.pdf.PdfDocument.Page
import com.example.test1.network.api.MainApiInterface
import com.example.test1.network.request.Async
import com.example.test1.network.response.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val mainApiInterface: MainApiInterface
) {

    suspend fun getMovieStream(page: Int) = flow {
        val result = mainApiInterface.getPopularMovies("38a73d59546aa378980a88b645f487fc","en-US",page)
        if(result.isSuccessful){
            result.body()?.results?.let {
                emit(Async.Success(it))
            } ?: run {
                emit(Async.Error(1))
            }
        }else {
            emit(Async.Error(0))
        }
    }
}