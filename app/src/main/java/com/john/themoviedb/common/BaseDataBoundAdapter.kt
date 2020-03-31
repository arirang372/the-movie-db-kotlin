package com.john.themoviedb.common

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseDataBoundAdapter<T : ViewDataBinding> :
    RecyclerView.Adapter<DataBoundViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<T> {
        return DataBoundViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<T>, position: Int) {
        bindItem(holder, position)
        holder.binding.executePendingBindings()
    }

    protected abstract fun bindItem(holder: DataBoundViewHolder<T>, position: Int)

    @LayoutRes
    abstract fun getItemLayoutId(position: Int): Int

}