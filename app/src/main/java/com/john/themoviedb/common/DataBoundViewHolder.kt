package com.john.themoviedb.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class DataBoundViewHolder<T : ViewDataBinding>(binding: T) :
    RecyclerView.ViewHolder(binding.root) {
    val binding: T = binding

    companion object {

        fun <T : ViewDataBinding> create(parent: ViewGroup, layoutId: Int): DataBoundViewHolder<T> {
            var binding =
                DataBindingUtil.inflate<T>(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )
            return DataBoundViewHolder(binding = binding)
        }
    }
}