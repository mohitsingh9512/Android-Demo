package com.example.test1.ui.viewholder.ui

import android.view.View
import com.example.test1.R
import com.example.test1.ui.viewholder.uimodel.EmptyDataModel

class EmptyViewHolder(view: View) : AbstractViewHolder<EmptyDataModel>(view){

    companion object {
        val LAYOUT = R.layout.item_view_empty
    }

    override fun bind(model: EmptyDataModel) {

    }
}