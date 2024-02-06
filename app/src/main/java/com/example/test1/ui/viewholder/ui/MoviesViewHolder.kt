package com.example.test1.ui.viewholder.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.test1.ui.adapter.MoviesInterface
import com.example.test1.R
import com.example.test1.ui.viewholder.uimodel.MoviesDataModel

class MoviesViewHolder(view: View, private val listener : MoviesInterface) : AbstractViewHolder<MoviesDataModel>(view){

    companion object {
        val LAYOUT = R.layout.item_view_movies
    }

    private val name : TextView by lazy {
        view.findViewById(R.id.movieName)
    }

    private val rating : TextView by lazy {
        view.findViewById(R.id.rating)
    }

    private val playlist : TextView by lazy {
        view.findViewById(R.id.playlist)
    }

    private val imageView : ImageView by lazy {
        view.findViewById(R.id.movieImage)
    }

    private val star : ImageView by lazy {
        view.findViewById(R.id.star)
    }

    override fun bind(model: MoviesDataModel) {
        name.text = model.movie.originalTitle
        rating.text = "Rating - ${model.movie.voteAverage}"
        playlist.text = model.movie.playlist

        if (!model.movie.posterPath.isNullOrEmpty()) {
            val imageUrl =
                "https://image.tmdb.org/t/p/w500${model.movie.posterPath}"
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop())
            Glide.with(itemView)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }

        star.setOnClickListener {
            listener.onStarClick(model.movie)
        }
    }

}