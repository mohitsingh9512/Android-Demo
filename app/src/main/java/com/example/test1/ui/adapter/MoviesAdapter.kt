package com.example.test1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.ui.viewholder.ui.AbstractViewHolder
import com.example.test1.ui.viewholder.ui.EmptyViewHolder
import com.example.test1.ui.viewholder.ui.MoviesViewHolder
import com.example.test1.ui.viewholder.uimodel.BaseDataModel
import com.example.test1.ui.viewholder.uimodel.MoviesDataModel

/*
    Using Visitor Pattern
 */
class MoviesAdapter(private val listener : MoviesInterface) : ListAdapter<BaseDataModel,AbstractViewHolder<*>>(MoviesDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val view  = createViewItem(parent,viewType)
        return when(viewType) {
            MoviesViewHolder.LAYOUT -> MoviesViewHolder(view,listener)
            else -> EmptyViewHolder(view)
        }
    }

    private fun createViewItem(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(viewType,parent,false)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        bind(holder as AbstractViewHolder<BaseDataModel>, position)
    }

    private fun bind(holder: AbstractViewHolder<BaseDataModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is MoviesDataModel -> MoviesViewHolder.LAYOUT
            else -> EmptyViewHolder.LAYOUT
        }
    }
}
