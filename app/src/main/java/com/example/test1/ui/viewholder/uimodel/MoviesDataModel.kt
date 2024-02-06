package com.example.test1.ui.viewholder.uimodel

import com.example.test1.network.response.Movie

class MoviesDataModel(
    override val uniqueId: Int,
    val movie: Movie
) : BaseDataModel