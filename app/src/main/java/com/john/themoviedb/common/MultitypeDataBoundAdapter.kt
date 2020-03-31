package com.john.themoviedb.common

import androidx.databinding.ViewDataBinding
import com.john.themoviedb.BR


open abstract class MultitypeDataBoundAdapter<T : ViewDataBinding> : BaseDataBoundAdapter<T>() {
    private val mItems = mutableListOf<Any>()

    override fun bindItem(holder: DataBoundViewHolder<T>, position: Int) {
        holder.binding.setVariable(BR.model, mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItem(position: Int): Any {
        return mItems[position]
    }

    fun addItem(item: Any) {
        mItems.add(item)
        notifyItemInserted(mItems.size - 1)
    }

    fun addItem(position: Int, item: Any) {
        mItems.add(position, item)
        notifyItemInserted(position)
    }
}