package com.example.test1.ui.viewholder.uimodel

import com.example.test1.network.request.Async
import com.example.test1.network.response.Movie

class MoviesDataModel(
    override val uniqueId: Int,
    val movie: Movie
) : BaseDataModel

fun List<Movie>.toUIModel(): Async<List<MoviesDataModel>> {
    val arrayListDataModel = arrayListOf<MoviesDataModel>()
    for (movie in  this){
        arrayListDataModel.add(MoviesDataModel(movie.id, movie))
    }
    return Async.Success(arrayListDataModel)
}