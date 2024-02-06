package com.example.test1.ui.viewholder.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<in Model>(view : View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Model)
}

sealed class LayoutType(val value: Int) {
    data object TYPE_EMPTY : LayoutType(1)
    data object TYPE_MOVIES : LayoutType(2)
}