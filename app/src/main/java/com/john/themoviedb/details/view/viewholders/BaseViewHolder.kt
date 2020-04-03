package com.john.themoviedb.details.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView


open abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}