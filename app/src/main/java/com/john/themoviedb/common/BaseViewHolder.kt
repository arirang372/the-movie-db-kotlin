package com.john.themoviedb.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView


open abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}